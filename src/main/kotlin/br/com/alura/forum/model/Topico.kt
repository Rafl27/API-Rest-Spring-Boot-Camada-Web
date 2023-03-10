package br.com.alura.forum.model

import java.time.LocalDateTime

data class Topico(
    var id: Long? = null,
    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val curso: Curso,
    val autor: Usuario,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    var respostas: MutableList<Resposta> = ArrayList()
) {

}