package com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.rest;

import com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.pedido.BuscarPedidosPorUsuarioUseCase;
import com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.usuario.AtualizarUsuarioUseCase;
import com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.usuario.BuscarUsuarioUseCase;
import com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.usuario.CriarUsuarioUseCase;
import com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.usuario.DeletarUsuarioUseCase;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido.PedidoResumoResponse;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.usuario.UsuarioRequest;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.usuario.UsuarioResponse;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@Tag(name = "Usuários", description = "Gerenciamento de usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final BuscarUsuarioUseCase buscarUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;
    private final BuscarPedidosPorUsuarioUseCase buscarPedidosPorUsuarioUseCase;

    public UsuarioController(
            CriarUsuarioUseCase criarUsuarioUseCase,
            BuscarUsuarioUseCase buscarUsuarioUseCase,
            AtualizarUsuarioUseCase atualizarUsuarioUseCase,
            DeletarUsuarioUseCase deletarUsuarioUseCase,
            BuscarPedidosPorUsuarioUseCase buscarPedidosPorUsuarioUseCase
    ) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.buscarUsuarioUseCase = buscarUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
        this.buscarPedidosPorUsuarioUseCase = buscarPedidosPorUsuarioUseCase;
    }

    /**
     * Cria um novo usuário
     * @param request
     * @return
     */
    @Operation(summary = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = UsuarioMapper.toModel(request);
        Usuario usuarioCriado = criarUsuarioUseCase.criar(usuario);
        UsuarioResponse response = UsuarioMapper.fromModel(usuarioCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Busca um usuário pelo ID
     * @param id
     * @return
     */
    @Operation(summary = "Busca um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable Long id) {
        Usuario usuario = buscarUsuarioUseCase.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.fromModel(usuario));
    }

    /**
     * Atualiza um usuário pelo ID
     * @param id
     * @param request
     * @return
     */
    @Operation(summary = "Atualiza um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        Usuario atualizado = atualizarUsuarioUseCase.atualizar(id, UsuarioMapper.toModel(request));
        return ResponseEntity.ok(UsuarioMapper.fromModel(atualizado));
    }

    /**
     * Deleta um usuário pelo ID
     * @param id
     * @return
     */
    @Operation(summary = "Deleta um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUsuarioUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lista os pedidos de um usuário pelo ID
     * @param id
     * @return
     */
    @Operation(summary = "Lista os pedidos de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos retornados com sucesso",
                    content = @Content(schema = @Schema(implementation = PedidoResumoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<PedidoResumoResponse>> listarPedidosPorUsuario(@PathVariable Long id) {
        Usuario usuario = buscarUsuarioUseCase.buscarPorId(id);
        List<PedidoResumoResponse> pedidos = buscarPedidosPorUsuarioUseCase.buscarPedidosPorUsuarioId(id);
        return ResponseEntity.ok(pedidos);
    }
}
