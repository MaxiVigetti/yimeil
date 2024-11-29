package edu.unsada.yimeil.controller;

import edu.unsada.yimeil.models.Attachments;
import edu.unsada.yimeil.models.Correo;
import edu.unsada.yimeil.models.DestinatariosFrom;
import edu.unsada.yimeil.models.Response;
import edu.unsada.yimeil.models.dto.*;
import edu.unsada.yimeil.repository.AttachmentsRepository;
import edu.unsada.yimeil.repository.CorreoRepository;
import edu.unsada.yimeil.repository.DestinatariosFromRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private CorreoRepository correoRepository;

    @Autowired
    private AttachmentsRepository attachmentsRepository;

    @Autowired
    private DestinatariosFromRepository destinatariosFromRepository;

    @CrossOrigin
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
            // Simulación de la lógica para enviar el correo
            boolean emailSent = enviarCorreo(emailRequest); // Aquí iría la lógica real para enviar el correo

            if (emailSent) {
                Correo nuevoCorreo = new Correo();
                nuevoCorreo.setSubject(emailRequest.getSubject());
                nuevoCorreo.setBody(emailRequest.getBody());
                nuevoCorreo.setReceivedAt(new Timestamp(System.currentTimeMillis()));
                nuevoCorreo.setFrom(emailRequest.getFrom());
                nuevoCorreo.setUserId(emailRequest.getSystemId());

                correoRepository.save(nuevoCorreo);

                // Guardar los destinatarios
                for (String to : emailRequest.getTo()) {
                    DestinatariosFrom destinatario = new DestinatariosFrom();
                    destinatario.setEmail(to);
                    destinatario.setCorreoEmailId(nuevoCorreo.getEmailId());
                    destinatariosFromRepository.save(destinatario);
                }

                // Guardar los adjuntos si existen
                if (emailRequest.getAttachments() != null && !emailRequest.getAttachments().isEmpty()) {
                    for (AttachmentResponse attachment : emailRequest.getAttachments()) {
                        Attachments newAttachment = new Attachments();
                        newAttachment.setFilename(attachment.getFilename());
                        newAttachment.setUrl(attachment.getUrl());
                        newAttachment.setCorreoEmailId(nuevoCorreo.getEmailId());
                        attachmentsRepository.save(newAttachment);
                    }
                }

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
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<CorreoResponse2>> getEmails(@RequestParam("token") String token, @RequestParam("systemId") String systemId) {
        if (token == null || token.isEmpty() || systemId == null || systemId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Correo> correos = correoRepository.findAll();
        List<CorreoResponse2> responseList = correos.stream()
                .map(correo -> new CorreoResponse2(
                        correo.getEmailId(),
                        destinatariosFromRepository.findByCorreoEmailId(correo.getEmailId()).stream()
                                .map(DestinatariosFrom::getEmail)
                                .collect(Collectors.toList()),
                        correo.getFrom(),
                        correo.getSubject(),
                        !attachmentsRepository.findByCorreoEmailId(correo.getEmailId()).isEmpty(),
                        correo.getReceivedAt().toString()
                ))
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/{emailId}")
        public ResponseEntity<CorreoResponse> getEmailDetails(
                @PathVariable("emailId") int emailId,
                @RequestParam("token") String token,
                @RequestParam("systemId") String systemId) {

            if (token == null || token.isEmpty() || systemId == null || systemId.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401 Unauthorized
            }

            Correo emailDetails = correoRepository.findById(emailId);

            if (emailDetails == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }

            List<DestinatariosFrom> destinatarios = destinatariosFromRepository.findByCorreoEmailId(emailId);
            List<Attachments> attachments = attachmentsRepository.findByCorreoEmailId(emailId);

            CorreoResponse emailResponse = new CorreoResponse(
                    emailDetails.getEmailId(),
                    destinatarios.stream().map(DestinatariosFrom::getEmail).collect(Collectors.toList()),
                    emailDetails.getFrom(),
                    emailDetails.getSubject(),
                    emailDetails.getBody(),
                    attachments.stream().map(att -> new AttachmentResponse(att.getFilename(), att.getUrl())).collect(Collectors.toList()),
                    emailDetails.getReceivedAt().toString()
            );

            return new ResponseEntity<>(emailResponse, HttpStatus.OK); // 200 OK
        }

        @DeleteMapping("{emailId}")
        public ResponseEntity<Void> deleteEmail(@PathVariable int emailId) {
            correoRepository.deleteById(emailId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


