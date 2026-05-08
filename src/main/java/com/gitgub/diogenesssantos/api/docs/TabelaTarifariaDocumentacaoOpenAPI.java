package com.gitgub.diogenesssantos.api.docs;


import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaRequestDTO;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.util.List;

@Tag(name = "TabelaTarifaController", description = "permitindo criar, atualizar, " +
        "consultar e deletar tabelas tarifarias.")
public interface TabelaTarifariaDocumentacaoOpenAPI {


    @Operation(summary = "Buscar todas as tabelas tarifaria salva no banco de dados .",
            description = "Retorna um conjunto [ ] JSON com todas tabelas tarifaria organizada pela " +
                    "data vigência mais atual e ativa ou uma list vazia caso não contenha tabelas tarifarias.",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(example = TabelaTarifariaRepresentacaoOpenAPI.SUCCESS_LOTE_)
                            )
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<TabelaTarifaria>> listarTabelas();



    @Operation(summary = "Salvar a tabela tarifaria no banco de dados.",
            description = "Retorna um conjunto JSON de uma tabela tarifaria salva.",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(example = TabelaTarifariaRepresentacaoOpenAPI.CORPO_EXEMPLO_GET_SUCESS)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(
                                                    name = "Formato inválido",
                                                    summary = "Campo com formato inválido no JSON",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_FORMAT
                                            ),
                                            @ExampleObject(
                                                    name = "Categoria nula ou vazia",
                                                    summary = "Categoria não pode ser null ou vazia",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_CATEGORIA_NOME
                                            ),
                                            @ExampleObject(
                                                    name = "Erro no campo ordem",
                                                    summary = "Faixas com ordem inválida",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_ORDEM
                                            ),
                                            @ExampleObject(
                                                    name = "Fim menor ou igual ao início",
                                                    summary = "fim deve ser maior que inicio",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_FIM_INICIO
                                            ),
                                            @ExampleObject(
                                                    name = "Continuidade de faixa inválida",
                                                    summary = "inicio deve ser fim anterior + 1",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_CONTINUIDADE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<TabelaTarifaria> criarTabela(@RequestBody(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(example = TabelaTarifariaRepresentacaoOpenAPI.CORPO_EXEMPLO_POST)))
                                                       TabelaTarifariaRequestDTO tabelaTarifariaRequest);





    @Operation(summary = "Salvar uma lista de tabelas tarifarias no banco de dados.",
            description = "Recebe uma lista de tabelas tarifarias e retorna a lista completa salva.",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(example = TabelaTarifariaRepresentacaoOpenAPI.SUCCESS_LOTE_)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(
                                                    name = "Formato inválido",
                                                    summary = "Campo com formato inválido no JSON",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_FORMAT
                                            ),
                                            @ExampleObject(
                                                    name = "Categoria nula ou vazia",
                                                    summary = "Categoria não pode ser null ou vazia",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_CATEGORIA_NOME
                                            ),
                                            @ExampleObject(
                                                    name = "Erro no campo ordem",
                                                    summary = "Faixas com ordem inválida",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_ORDEM
                                            ),
                                            @ExampleObject(
                                                    name = "Fim menor ou igual ao início",
                                                    summary = "fim deve ser maior que inicio",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_FIM_INICIO
                                            ),
                                            @ExampleObject(
                                                    name = "Continuidade de faixa inválida",
                                                    summary = "inicio deve ser fim anterior + 1",
                                                    value = TabelaTarifariaRepresentacaoOpenAPI.BAD_REQUEST_CONTINUIDADE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<TabelaTarifaria>> criarTabelaEmLote(@RequestBody(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(example = TabelaTarifariaRepresentacaoOpenAPI.CORPO_EXEMPLO_POST_LOTE)))
                                                                   List<TabelaTarifariaRequestDTO> tabelaTarifariaRequests);



    @Operation(summary = "Buscar uma tabela tarifaria pelo ID.",
            description = "Retorna os dados de uma tabela tarifaria a partir do seu ID.",
            parameters = {
                    @Parameter(name = "id", description = "ID da tabela tarifaria", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(example = TabelaTarifariaRepresentacaoOpenAPI.CORPO_EXEMPLO_GET_SUCESS)
                            )
                    ),
                    @ApiResponse(description = "Not Found", responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(example = TabelaTarifariaRepresentacaoOpenAPI.NOT_FOUND_TABELA)
                            )
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    ResponseEntity<TabelaTarifaria> buscarPorId(Long id);


    @Operation(summary = "Deletar uma tabela tarifaria pelo ID.",
            description = "Deleta uma tabela tarifaria a partir do seu ID, sem retorno de corpo mesmo se a tabela não existir.",
            parameters = {
                    @Parameter(name = "id", description = "ID da tabela tarifaria a ser deletada", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Void> deletaTabelaPorId(Long id);

}
