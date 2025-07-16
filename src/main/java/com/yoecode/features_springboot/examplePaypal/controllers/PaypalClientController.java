package com.yoecode.features_springboot.examplePaypal.controllers;

import com.yoecode.features_springboot.examplePaypal.models.CreateOrderPaypalRequest;
import com.yoecode.features_springboot.examplePaypal.models.CreateOrderPaypalResponse;
import com.yoecode.features_springboot.examplePaypal.services.PaypalClientService;
import org.springframework.web.bind.annotation.RestController;
import com.paypal.orders.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
@RequestMapping("/paypal")
@Tag(name = "PayPal Payment", description = "API para gestionar pagos con PayPal")
public class PaypalClientController {
    private static final Logger logger = LoggerFactory.getLogger(PaypalClientController.class);

    @Autowired
    private PaypalClientService payPalService;

    @Operation(summary = "Crear una nueva orden de pago",
            description = "Crea una nueva orden de pago en PayPal y retorna la URL de aprobación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden creada exitosamente",
                    content = @Content(schema = @Schema(implementation = CreateOrderPaypalResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/orders")
    public ResponseEntity<CreateOrderPaypalResponse> createOrder(
            @Valid @RequestBody CreateOrderPaypalRequest request) {
        try {
            logger.info("Solicitud para crear orden recibida: {} {}",
                    request.getAmount(), request.getCurrency());

            CreateOrderPaypalResponse response = payPalService.createOrder(request);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            logger.error("Error al crear orden de PayPal", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Capturar una orden aprobada",
            description = "Captura el pago de una orden que ha sido aprobada por el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden capturada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/orders/{orderId}/capture")
    public ResponseEntity<Order> captureOrder(
            @Parameter(description = "ID de la orden a capturar", required = true)
            @PathVariable String orderId) {
        try {
            logger.info("Solicitud para capturar orden: {}", orderId);

            Order order = payPalService.captureOrder(orderId);
            return ResponseEntity.ok(order);

        } catch (IOException e) {
            logger.error("Error al capturar orden de PayPal: {}", orderId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Obtener detalles de una orden",
            description = "Obtiene los detalles completos de una orden específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrderDetails(
            @Parameter(description = "ID de la orden", required = true)
            @PathVariable String orderId) {
        try {
            logger.info("Solicitud para obtener detalles de orden: {}", orderId);

            Order order = payPalService.getOrderDetails(orderId);
            return ResponseEntity.ok(order);

        } catch (IOException e) {
            logger.error("Error al obtener detalles de orden: {}", orderId, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
