package com.yoecode.features_springboot.examplePaypal.models;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta al crear una orden de pago")
public class CreateOrderPaypalResponse {

    @Schema(description = "ID de la orden creada", example = "5O190127TN364715T")
    private String orderId;

    @Schema(description = "Estado de la orden", example = "CREATED")
    private String status;

    @Schema(description= "URL de aprobación para redirigir al usuario",
            example = "https://www.sandbox.paypal.com/checkoutnow?token=5O190127TN364715T")
    private String approvalUrl;

    // Constructores
    public CreateOrderPaypalResponse() {}

    public CreateOrderPaypalResponse(String orderId, String status, String approvalUrl) {
        this.orderId = orderId;
        this.status = status;
        this.approvalUrl = approvalUrl;
    }

    // Getters y Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getApprovalUrl() { return approvalUrl; }
    public void setApprovalUrl(String approvalUrl) { this.approvalUrl = approvalUrl; }
}
