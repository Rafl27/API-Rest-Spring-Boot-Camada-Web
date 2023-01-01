package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


//Quando é um DTO de entrada, como este, onde o usuário está enviando os dados, uma boa prática é nomear o DTO como FORm
data class NovoTopicoForm(
    @field:NotEmpty @Size(min = 5, max = 100) val titulo: String,
    @field:NotEmpty val mensagem: String,
    @field:NotNull val idCurso: Long,
    @field:NotNull val idAutor: Long
) {

}
