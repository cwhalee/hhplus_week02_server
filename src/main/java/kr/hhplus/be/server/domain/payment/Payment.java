package kr.hhplus.be.server.domain.payment;

import java.time.LocalDateTime;

public class Payment {
    private final Long paymentId;
    private final Long reservationId;
    private final long amount;
    private final PaymentStatus status;
    private final LocalDateTime paidAt;

    public Payment(Long paymentId, Long reservationId, long amount,
                   PaymentStatus status, LocalDateTime paidAt) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.amount = amount;
        this.status = status;
        this.paidAt = paidAt;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}