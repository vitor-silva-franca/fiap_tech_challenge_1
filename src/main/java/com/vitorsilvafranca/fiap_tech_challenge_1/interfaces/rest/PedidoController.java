package com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.rest;

import com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.pedido.AtualizarPedidoUseCase;
import com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.pedido.BuscarPedidoUseCase;
import com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.pedido.CriarPedidoUseCase;
import com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.pedido.DeletarPedidoUseCase;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.Pedido;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido.PedidoRequest;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido.PedidoResponse;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.mapper.PedidoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.net.URI;

@Tag(name = "Pedidos", description = "Gerenciamento de pedidos")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final AtualizarPedidoUseCase atualizarPedidoUseCase;
    private final DeletarPedidoUseCase deletarPedidoUseCase;

    public PedidoController(
            CriarPedidoUseCase criarPedidoUseCase,
            BuscarPedidoUseCase buscarPedidoUseCase,
            AtualizarPedidoUseCase atualizarPedidoUseCase,
            DeletarPedidoUseCase deletarPedidoUseCase
    ) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.buscarPedidoUseCase = buscarPedidoUseCase;
        this.atualizarPedidoUseCase = atualizarPedidoUseCase;
        this.deletarPedidoUseCase = deletarPedidoUseCase;
    }

    /**
     * Cria um novo pedido
     * @param request
     * @return
     */
    @Operation(summary = "Cria um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                    content = @Content(schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@Valid @RequestBody PedidoRequest request) {
        Pedido pedido = criarPedidoUseCase.criar(PedidoMapper.toResumo(request));
        return ResponseEntity.created(URI.create("/pedidos/" + pedido.getId()))
                .body(PedidoMapper.fromModel(pedido));
    }

    /**
     * Busca um pedido pelo ID
     * @param id
     * @return
     */
    @Operation(summary = "Busca um pedido pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                    content = @Content(schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscar(@PathVariable Long id) {
        Pedido pedido = buscarPedidoUseCase.buscarPorId(id);
        return ResponseEntity.ok(PedidoMapper.fromModel(pedido));
    }

    /**
     * Atualiza um pedido existente
     * @param id
     * @param request
     * @return
     */
    @Operation(summary = "Atualiza um pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PedidoRequest request) {
        Pedido atualizado = atualizarPedidoUseCase.atualizar(id, PedidoMapper.toResumo(request));
        return ResponseEntity.ok(PedidoMapper.fromModel(atualizado));
    }

    /**
     * Deleta um pedido pelo ID
     * @param id
     * @return
     */
    @Operation(summary = "Deleta um pedido pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarPedidoUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
