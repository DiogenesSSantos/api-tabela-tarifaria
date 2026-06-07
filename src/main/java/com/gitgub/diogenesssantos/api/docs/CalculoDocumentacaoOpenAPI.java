package com.gitgub.diogenesssantos.api.docs;

import com.gitgub.diogenesssantos.api.dtos.calculos.CalculoRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.CalculoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "CalculosController", description = "permitindo calcular, por consumo m³.")
public interface CalculoDocumentacaoOpenAPI {

    @Operation(summary = "Calcular consumo a parti de uma tabela tarifaria.",
            description = "Retorna um JSON com todas informações sobre consumo de uma determinada categoria " +
                    "pelo respective faixa de consumo.",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(example = CalculoRepresentacaoOpenAPI.SUCCESS)
                            )
                    ),
                    @ApiResponse(description = "Bad request", responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(
                                                    name = "Erro JSON campos inválidos. Consumo.",
                                                    summary = "O corpo da requisição incorreto, Consumo." +
                                                            " inválidos e corrigia seguindo a instrução.",
                                                    value = CalculoRepresentacaoOpenAPI.BAD_REQUEST_CONSUMO
                                            ),
                                            @ExampleObject(
                                                    name = "Erro JSON campos inválidos. Categoria.",
                                                    summary = "O corpo da requisição incorreto, Categoria." +
                                                            " inválidos e corrigia seguindo a instrução.",
                                                    value = CalculoRepresentacaoOpenAPI.BAD_REQUEST_CATEGORIA
                                            )}
                            )
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    ResponseEntity<CalculoResponseDTO> calcular(@RequestBody(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(example = CalculoRepresentacaoOpenAPI.CORPO_EXEMPLO)
            )) CalculoRequestDTO req);
}
