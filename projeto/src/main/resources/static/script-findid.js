function apresentarDadosLivro() {
    const dadosLivro = document.getElementById('livro-details');
    const livroId = document.getElementById('livro-id').value;

    dadosLivro.innerHTML = ``;

    if (!livroId) {
        alert("O campo ID é obrigatório");
        return;
    }
    console.log(livroId)

    const uri = `http://localhost:8081/livros/id/${livroId}`;

    async function fetchLivroData() {
        try {
            const response = await fetch(uri);

            if (!response.ok) {
                alert("Não foi possível retornar os dados do livro!");
            } else {
                const json = await response.json();
                dadosLivro.innerHTML = 
                `<p><strong>Título:</strong> ${json.titulo}</p>
                 <p><strong>Autor:</strong> ${json.autor}</p>
                 <p><strong>Gênero:</strong> ${json.genero}</p>
                 <p><strong>Ano de Publicação:</strong> ${json.anoPublicacao}</p>
                 <p><strong>Disponível:</strong> ${json.disponivel ? 'Sim' : 'Não'}</p>`;
            }
        } catch (error) {
            alert("Erro ao conectar com a API: " + error.message);
        }
    }

    fetchLivroData();
}