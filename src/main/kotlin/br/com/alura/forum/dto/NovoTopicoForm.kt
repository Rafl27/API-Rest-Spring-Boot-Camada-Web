package br.com.alura.forum.dto


//Quando é um DTO de entrada, como este, onde o usuário está enviando os dados, uma boa prática é nomear o DTO como FORm
data class NovoTopicoForm(
    val titulo: String,
    val mensagem: String,
    val idCurso: Long,
    val idAutor: Long
) {

}
