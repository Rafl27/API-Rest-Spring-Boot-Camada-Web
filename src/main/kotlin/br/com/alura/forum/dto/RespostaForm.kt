package br.com.alura.forum.dto

import br.com.alura.forum.model.Topico
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.NotNull

data class RespostaForm(
//    @field:NotEmpty
//    @Size(min = 3, max = 500)
    val mensagem: String,
//    @field:NotEmpty
    val topicoId: Long,
//    @field:NotEmpty
    val idAutor: Long,
//    @field:NotNull
//    val solucao: Boolean
    ) {
}