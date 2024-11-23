package edu.unsada.yimeil.controller;

import edu.unsada.yimeil.models.Attachments;
import edu.unsada.yimeil.models.Correo;
import edu.unsada.yimeil.models.DestinatariosFrom;
import edu.unsada.yimeil.models.Response;
import edu.unsada.yimeil.models.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emails")
public class EmailController {


    @PostMapping
    public ResponseEntity<Response> sendEmail(@RequestBody EmailRequestDTO emailRequest) {
        if (emailRequest.getToken() == null || emailRequest.getToken().isEmpty() ||
                emailRequest.getSystemId() == null || emailRequest.getSystemId().isEmpty()) {
            return new ResponseEntity<>(new Response(false, "Token o systemId no proporcionado."), HttpStatus.UNAUTHORIZED);
        }

        if (emailRequest.getTo() == null || emailRequest.getTo().isEmpty() ||
                emailRequest.getFrom() == null || emailRequest.getFrom().isEmpty()) {
            return new ResponseEntity<>(new Response(false, "Información del remitente o destinatario no proporcionada."), HttpStatus.BAD_REQUEST);
        }

        try {
            // Aquí iría la lógica para enviar el correo utilizando la API de Yimeil
            boolean emailSent = enviarCorreo(emailRequest); // Lógica para enviar el correo

            if (emailSent) {
                return new ResponseEntity<>(new Response(true, "Correo enviado exitosamente."), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new Response(false, "Error al enviar el correo."), HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, "Error al enviar el correo: " + e.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    private boolean enviarCorreo(EmailRequestDTO emailRequest) {
        // Simulación de la lógica para enviar un correo
        // Deberías reemplazar esto con la implementación real
        return true; // Devuelve true si el correo se envía exitosamente
    }


    // Endpoint para obtener la lista de correos recibidos
    @GetMapping
    public ResponseEntity<List<CorreoResponse2>> getEmails(@RequestHeader("token") String token, @RequestHeader("systemId") String systemId) {
        if (token == null || token.isEmpty() || systemId == null || systemId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Correo sampleEmail = new Correo(
                1, "Asunto del correo", "Cuerpo del correo",
                new Timestamp(System.currentTimeMillis()), "userId",
                Arrays.asList(new Attachments(1, "archivo.txt", "http://url.al.archivo", 1, null)),
                Arrays.asList(new DestinatariosFrom(1, "dest@example.com", 1, null))
        );
        sampleEmail.setFrom("remitente@example.com");
        sampleEmail.setTo(Arrays.asList("dest1@example.com", "dest2@example.com"));

        CorreoResponse2 emailResponse = new CorreoResponse2(
                sampleEmail.getEmailId(),
                sampleEmail.getTo(),
                sampleEmail.getFrom(),
                sampleEmail.getSubject(),
                !sampleEmail.getAttachmentsByEmailId().isEmpty(),
                sampleEmail.getReceivedAt().toString()
        );
        return new ResponseEntity<>(Arrays.asList(emailResponse), HttpStatus.OK);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<CorreoResponse> getEmailDetails(@PathVariable("emailId") int emailId, @RequestHeader("token") String token, @RequestHeader("systemId") String systemId) {
        // Verificar si el token o el systemId están vacíos o nulos
        if (token == null || token.isEmpty() || systemId == null || systemId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401 Unauthorized
        }

        // Simulación de la lógica para obtener los detalles del correo
        // En una aplicación real, esto debería obtener los datos de la base de datos
        Correo emailDetails = obtenerCorreoPorId(emailId);

        // Verificar si el correo no existe
        if (emailDetails == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }

        // Crear la respuesta con los detalles del correo
        CorreoResponse emailResponse = new CorreoResponse(
                emailDetails.getEmailId(),
                emailDetails.getTo(),
                emailDetails.getFrom(),
                emailDetails.getSubject(),
                emailDetails.getBody(),
                emailDetails.getAttachmentsByEmailId().stream()
                        .map(att -> new AttachmentResponse(att.getFilename(), att.getUrl()))
                        .collect(Collectors.toList()),
                emailDetails.getReceivedAt().toString()
        );

        return new ResponseEntity<>(emailResponse, HttpStatus.OK); // 200 OK
    }

    private Correo obtenerCorreoPorId(int emailId) {
        // Simulación de la lógica para obtener un correo por su ID
        // Deberías reemplazar esto con la implementación real
        if (emailId == 1) {
            return new Correo(
                    emailId, "Asunto del correo", "Cuerpo del correo",
                    new Timestamp(System.currentTimeMillis()), "userId",
                    Arrays.asList(new Attachments(1, "archivo.txt", "http://url.al.archivo", emailId, null)),
                    Arrays.asList(new DestinatariosFrom(1, "dest@example.com", emailId, null))
            );
        }
        return null; // Simula un correo no existente para otros ID
    }
}