package com.netcommon.executor;

import com.vip.osp.core.exception.OspException;
import com.vip.vis.stock.analysis.modules.pr.bo.TransitParamBO;
import com.vip.vis.stock.analysis.modules.pr.business.AsynNetworkDelayQueue;
import com.vip.vis.stock.analysis.modules.pr.business.CisOnWayBusiness;
import com.vip.vis.stock.analysis.modules.pr.business.TransitOffsetNetworkDelayQueue;
import com.vip.vis.stock.analysis.modules.pr.business.impl.CisOnWayBusinessImpl;
import com.vip.vis.stock.analysis.modules.pr.constant.Constants;
import com.vip.vis.stock.analysis.modules.pr.exception.ExceptionLogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步延时调用
 *
 * @author bing
 * @version 1.0
 * @create 2018/3/15
 **/
@Component
public class AsynNetworkDelayQueueImpl implements AsynNetworkDelayQueue {
    private static DelayQueue<Delayed> delayQueue = new DelayQueue<>();
    @Autowired
    private CisOnWayBusiness cisOnWayBusiness;
    @Autowired
    private TransitOffsetNetworkDelayQueue transitOffsetNetworkDelayQueue;
    private static final Logger logger = LoggerFactory.getLogger(Constants.PR_LOGGER_FILE_NAME);
    /**
     * 制定delay规则
     * @param delayed
     */
    private void addDelayQueue(Delayed delayed) {
        delayQueue.add(delayed);
    }

    /**
     * 处理delay任务
     * @throws OspException
     */
    private void consumeAction() throws OspException {
        while (!delayQueue.isEmpty()) {
            //此处会阻塞
            DelayEvent delayEvent = null;
            TransitParamBO transitParamBO = null;
            try {
                delayEvent = (DelayEvent) delayQueue.take();
                transitParamBO = delayEvent.getTransitParamBO();
                boolean isSuccess = cisOnWayBusiness.pushOnWayOrder(transitParamBO);
                if (!isSuccess) {
                    transitOffsetNetworkDelayQueue.offsetRpcTask(transitParamBO);
                }
            } catch (OspException | InterruptedException e) {
                transitOffsetNetworkDelayQueue.offsetRpcTask(transitParamBO);
                ExceptionLogUtils.throwExceptionAndLogIDE(AsynNetworkDelayQueueImpl.class,transitParamBO,e);
            }


        }
    }
    /**
     * 多线程延时调用
     *
     * @return
     * @throws Exception
     */
    @Override
    public void executeRpcTask(List<TransitParamBO> transitParamBOs) {
        logger.info("executeRpcTask transitParamBOs = {}",transitParamBOs);
        ThreadPoolExecutor threadPoolExecutor = AsynTaskExecutors.getInstance().getService();
        for (final TransitParamBO transitParamBO : transitParamBOs){
            threadPoolExecutor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    DelayEvent delayEvent = new DelayEvent(System.currentTimeMillis() + DelayEvent.DELAY_TIME, transitParamBO);
                    addDelayQueue(delayEvent);
                    consumeAction();
                    return CisOnWayBusinessImpl.SUCCESS;
                }
            });

        }
        threadPoolExecutor.shutdown();
    }
}
