package com.yoecode.features_springboot.examplePaypal.models;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "Datos para crear una orden de pago")
public class CreateOrderPaypalRequest {

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @Schema(description = "Monto total de la orden", example = "100.00")
    private BigDecimal amount;

    @NotBlank(message = "La moneda es obligatoria")
    @Schema(description = "Código de moneda", example = "USD")
    private String currency;

    @Schema(description = "Descripción del producto/servicio", example = "Compra de producto")
    private String description;

    @Schema(description = "URL de retorno exitoso", example = "https://example.com/success")
    private String returnUrl;

    @Schema(description = "URL de cancelación", example = "https://example.com/cancel")
    private String cancelUrl;

    // Constructores
    public CreateOrderPaypalRequest() {}

    public CreateOrderPaypalRequest(BigDecimal amount, String currency, String description, String returnUrl, String cancelUrl) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
    }

    // Getters y Setters
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReturnUrl() { return returnUrl; }
    public void setReturnUrl(String returnUrl) { this.returnUrl = returnUrl; }

    public String getCancelUrl() { return cancelUrl; }
    public void setCancelUrl(String cancelUrl) { this.cancelUrl = cancelUrl; }
}

