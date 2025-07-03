package com.example.EcoMarket_SPA.Factura;

import com.example.EcoMarket_SPA.Assemblers.FacturaModelAssemblers;
import com.example.EcoMarket_SPA.Controller.FacturaController;
import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Model.Factura;
import com.example.EcoMarket_SPA.Model.Venta;
import com.example.EcoMarket_SPA.Services.FacturaServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FacturaController.class)
public class FacturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacturaServices facturaServices;

    @MockBean
    private FacturaModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllFacturas() throws Exception {
        Factura factura = new Factura(1, LocalDate.of(2024, 7, 1), 19990.0, new Venta(), new Cliente());
        List<Factura> lista = List.of(factura);

        when(facturaServices.getFacturas()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(CollectionModel.of(List.of(EntityModel.of(factura))));

        mockMvc.perform(get("/api/facturas"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("19990.0")));
    }

    @Test
    void testGetFacturaById() throws Exception {
        Factura factura = new Factura(1, LocalDate.of(2024, 7, 1), 19990.0, new Venta(), new Cliente());

        when(facturaServices.obtenerFactura(1)).thenReturn(factura);
        when(assembler.toModel(factura)).thenReturn(EntityModel.of(factura));

        mockMvc.perform(get("/api/facturas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(19990.0));
    }

    @Test
    void testCreateFactura() throws Exception {
        Factura factura = new Factura(1, LocalDate.of(2024, 7, 1), 19990.0, new Venta(), new Cliente());

        when(facturaServices.guardarFactura(factura)).thenReturn(factura);

        mockMvc.perform(post("/api/facturas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(factura)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(19990.0));
    }

    @Test
    void testDeleteFactura() throws Exception {
        when(facturaServices.eliminarFactura(1)).thenReturn(true);

        mockMvc.perform(delete("/api/facturas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Factura eliminada"));
    }
}
