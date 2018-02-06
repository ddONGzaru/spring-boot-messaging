package io.manasobi.messaging.listener;

import io.manasobi.messaging.annotation.Log;
import io.manasobi.messaging.event.CurrencyEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;


@Component
public class RateEventListener {

	//@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	@TransactionalEventListener
	@Log(printParamsValues = true, callMethodWithNoParamsToString = "getRate")
	public void processEvent(CurrencyEvent event){ }
}
