package com.acme.web.services.core.integration.tests;
import com.acme.web.services.iam.domain.model.aggregates.User;
import com.acme.web.services.iam.domain.model.commands.SignInCommand;
import com.acme.web.services.iam.domain.model.commands.SignUpCommand;
import com.acme.web.services.iam.domain.services.UserCommandService;
import com.acme.web.services.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.acme.web.services.iam.interfaces.rest.resources.UserResource;
import com.acme.web.services.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.acme.web.services.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserCommandService userCommandService;
    // Test para sign-in exitoso
    @Test
    void testSignInSuccess() throws Exception {
        // Simular el dominio de usuario autenticado
        User mockUser = new User(); // Crea una instancia mock de User
        String mockToken = "token123";
        ImmutablePair<User, String> mockPair = new ImmutablePair<>(mockUser, mockToken);
        // Asigna un recurso a partir del dominio
        AuthenticatedUserResource authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(mockUser, mockToken);
        // Mockea el método handle de SignInCommand
        Mockito.when(userCommandService.handle(isA(SignInCommand.class)))
                .thenReturn(Optional.of(mockPair));
        // Enviar la solicitud de sign-in
        mockMvc.perform(post("/api/v1/authentication/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"testuser\", \"password\": \"testpassword\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(authenticatedUserResource.username()))
                .andExpect(jsonPath("$.token").value(authenticatedUserResource.token()));
    }
    // Test para sign-up exitoso
    @Test
    void testSignUpSuccess() throws Exception {
        // Simular el dominio de creación de usuario
        User mockUser = new User(); // Crea una instancia mock de User
        // Asigna un recurso a partir del dominio
        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(mockUser);
        // Mockea el método handle de SignUpCommand
        Mockito.when(userCommandService.handle(isA(SignUpCommand.class)))
                .thenReturn(Optional.of(mockUser));
        // Enviar la solicitud de sign-up
        mockMvc.perform(post("/api/v1/authentication/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"newuser\", \"password\": \"newpassword\", \"email\": \"newuser@example.com\" }"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(userResource.username()));
    }
}
