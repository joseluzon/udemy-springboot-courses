package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.controllers;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.controllers.exceptions.ErrorResponseAttributes;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.dto.ContactDto;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.services.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Tag(name = "Contact Controller",
        description = "Provides operation for Contacts management (Retrieve, create, update, delete)")
public class ContactController {
    // @Autowired by Ctor.
    private final ContactService contactService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves all Contacts",
            description = "Provides a list containing all contacts")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of contacts",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ContactDto.class))))
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        return new ResponseEntity<>(contactService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrives Contact by id",
            description = "Returns a Contact based on the given id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", description = "Contact doesn't exist",
                            content = @Content(schema = @Schema(
                                    implementation = ErrorResponseAttributes.class))),
                    @ApiResponse(responseCode = "200",
                            description = "Successful retrieval of contact", content = @Content(
                                    schema = @Schema(implementation = ContactDto.class))),})
    public ResponseEntity<ContactDto> getContact(@PathVariable("id") final UUID id) {
        return new ResponseEntity<>(contactService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creates a contact",
            description = "Creates the contact from the provided payload and returns the created contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of contact",
                    content = @Content(schema = @Schema(implementation = ContactDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseAttributes.class)))})
    public ResponseEntity<ContactDto> createContact(@RequestBody @Valid final ContactDto contact) {
        return new ResponseEntity<>(contactService.createContact(contact), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates a contact",
            description = "Updates the contact based on the given id and the provided payload. Returns the updated contact")
    public ResponseEntity<ContactDto> updateContact(@PathVariable("id") final UUID id,
            @RequestBody @Valid final ContactDto contact) {
        return new ResponseEntity<>(contactService.updateContact(id, contact), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a contact",
            description = "Deletes the contact based on the given id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", description = "Contact doesn't exist",
                            content = @Content(schema = @Schema(
                                    implementation = ErrorResponseAttributes.class))),
                    @ApiResponse(responseCode = "204",
                            description = "Successful deletion of contact")})
    public ResponseEntity<Void> deleteContact(@PathVariable("id") final UUID id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
