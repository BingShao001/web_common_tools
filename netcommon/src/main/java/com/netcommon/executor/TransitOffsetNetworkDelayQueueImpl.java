package com.netcommon.executor;

import com.vip.osp.core.exception.OspException;
import com.vip.vis.stock.analysis.modules.pr.bo.TransitParamBO;
import com.vip.vis.stock.analysis.modules.pr.business.CisOnWayBusiness;
import com.vip.vis.stock.analysis.modules.pr.business.TransitOffsetNetworkDelayQueue;
import com.vip.vis.stock.analysis.modules.pr.business.impl.CisOnWayBusinessImpl;
import com.vip.vis.stock.analysis.modules.pr.exception.ExceptionLogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 补偿机制
 *
 * @author bing
 * @version 1.0
 * @create 2018/3/12
 **/
@Component
class TransitOffsetNetworkDelayQueueImpl implements TransitOffsetNetworkDelayQueue {
    private static DelayQueue<Delayed> delayQueue = new DelayQueue<>();
    @Autowired
    private CisOnWayBusiness cisOnWayBusiness;


    /**
     * 制定delay规则
     *
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
            TransitParamBO transitParamBO = null;
            try {
                //此处会阻塞
                EventOffsetDelay eventOffsetDelay = (EventOffsetDelay) delayQueue.take();
                transitParamBO = eventOffsetDelay.getTransitParamBO();
                cisOnWayBusiness.pushOnWayOrder(transitParamBO);
            } catch (InterruptedException e) {
                ExceptionLogUtils.throwExceptionAndLog(TransitOffsetNetworkDelayQueueImpl.class, transitParamBO, e);
            }
        }
    }

    /**
     * 补偿多线程延时调用
     *
     * @return
     * @throws Exception
     */
    @Override
    public void offsetRpcTask(final TransitParamBO transitParamBO) throws OspException {
        ThreadPoolExecutor threadPoolExecutor = AsynTaskExecutors.getInstance().getService();
        threadPoolExecutor.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                EventOffsetDelay offsetDelay = new EventOffsetDelay(System.currentTimeMillis() + EventOffsetDelay.DELAY_TIME, transitParamBO);
                addDelayQueue(offsetDelay);
                consumeAction();
                return CisOnWayBusinessImpl.SUCCESS;
            }
        });

        threadPoolExecutor.shutdown();
    }
}
