package br.com.alura.forum.dto

import br.com.alura.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoView(
    //Question mark makes the ID optional.
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
) {

}
