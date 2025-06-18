function apresentarDadosLivro() {
    var idLivro = document.getElementById('livro-id').value;

    if (!idLivro) {
        alert("Por favor, digite um ID válido");
        return;
    }

    const uri = `http://localhost:8081/livros/id/${idLivro}`;

    async function buscarDadosLivro() {
        const resposta = await fetch(uri);
        if (!resposta.ok) {
            alert("Não foi possível retornar os dados do livro!");
        } else {
            const json = await resposta.json();
            apresentarLivro(json);
        }
    }

    function apresentarLivro(livro) {
        var dadosLivro = document.getElementById('livro-details');

        var html = '<h2>Detalhes do Livro</h2>' +
            '<table>' +
            '<tr><th>ID</th><td>' + livro.id + '</td></tr>' +
            '<tr><th>Título</th><td>' + livro.titulo + '</td></tr>' +
            '<tr><th>Autor</th><td>' + livro.autor + '</td></tr>' +
            '<tr><th>Gênero</th><td>' + livro.genero + '</td></tr>' +
            '<tr><th>Ano de Publicação</th><td>' + livro.anoPublicacao + '</td></tr>' +
            '<tr><th>Disponível</th><td>' + (livro.disponivel ? 'Sim' : 'Não') + '</td></tr>' +
            '</table>';

        dadosLivro.innerHTML = html;
    }

    buscarDadosLivro();
}

function apresentarTodosLivros() {
    limparTela();

    const uri = `http://localhost:8081/livros`;

    async function buscarTodosLivros() {
        const resposta = await fetch(uri);
        if (!resposta.ok) {
            alert("Não foi possível retornar os dados dos livros!");
        } else {
            const json = await resposta.json();
            montarTabela(json);
        }
    }

    function montarTabela(livros) {
        var areaTabela = document.getElementById('all-livros');
        var tabelaLivros = document.getElementById('livros-table-body');

        areaTabela.classList.remove('hidden');
        tabelaLivros.innerHTML = '';

        for (var i = 0; i < livros.length; i++) {
            var livro = livros[i];
            var linhaAtual = document.createElement('tr');
            linhaAtual.innerHTML =
                '<td>' + livro.id + '</td>' +
                '<td>' + livro.titulo + '</td>' +
                '<td>' + livro.autor + '</td>' +
                '<td>' + livro.genero + '</td>' +
                '<td>' + livro.anoPublicacao + '</td>' +
                '<td>' + (livro.disponivel ? 'Sim' : 'Não') + '</td>';
            tabelaLivros.appendChild(linhaAtual);
        }
    }

    buscarTodosLivros();
}

function limparTela() {
    document.getElementById('livro-details').innerHTML = '';
    document.getElementById('livros-table-body').innerHTML = '';
    document.getElementById('all-livros').classList.add('hidden');
    document.getElementById('livro-id').value = '';
}