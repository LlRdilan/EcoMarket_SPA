package com.example.EcoMarket_SPA.Cupon;

import com.example.EcoMarket_SPA.Assemblers.CuponModelAssemblers;
import com.example.EcoMarket_SPA.Controller.CuponController;
import com.example.EcoMarket_SPA.Model.Cupon;
import com.example.EcoMarket_SPA.Services.CuponServices;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CuponController.class)
public class CuponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuponServices cuponServices;

    @MockBean
    private CuponModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCupons() throws Exception {
        Cupon cupon = new Cupon(1, "DESC10", 10.0, LocalDate.now().plusDays(5), true);
        List<Cupon> lista = List.of(cupon);

        when(cuponServices.getCupons()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(
                CollectionModel.of(List.of(EntityModel.of(cupon)))
        );

        mockMvc.perform(get("/api/cupones"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("DESC10")));
    }

    @Test
    void testGetCuponById() throws Exception {
        Cupon cupon = new Cupon(1, "DESC10", 10.0, LocalDate.now().plusDays(5), true);

        when(cuponServices.obtenerCupon(1)).thenReturn(cupon);
        when(assembler.toModel(cupon)).thenReturn(EntityModel.of(cupon));

        mockMvc.perform(get("/api/cupones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("DESC10"));
    }

    @Test
    void testCreateCupon() throws Exception {
        Cupon cupon = new Cupon(1, "DESC10", 10.0, LocalDate.now().plusDays(5), true);

        when(cuponServices.guardarCupon(cupon)).thenReturn(cupon);

        mockMvc.perform(post("/api/cupones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cupon)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("DESC10"));
    }

    @Test
    void testDeleteCupon() throws Exception {
        when(cuponServices.eliminarCupon(1)).thenReturn(true);

        mockMvc.perform(delete("/api/cupones/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cupón eliminado"));
    }
}
