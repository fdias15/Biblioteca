INSERT INTO tb_usuario (nome, email, senha) VALUES
('Amanda Dias', 'amanda@email.com', '123'),
('João Silva', 'joao@email.com', '123'),
('Mariana Lima', 'mariana.lima@email.com', '123'),
('Carlos Alberto', 'carlos.alberto@email.com', '123'),
('Fernanda Souza', 'fernanda.souza@email.com', '123'),
('Roberto Oliveira', 'roberto.oliveira@email.com', '123'),
('Patrícia Mendes', 'patricia.mendes@email.com', '123'),
('André Rocha', 'andre.rocha@email.com', '123'),
('Juliana Castro', 'juliana.castro@email.com', '123'),
('Lucas Martins', 'lucas.martins@email.com', '123'),
('Gabriela Costa', 'gabriela.costa@email.com', '123'),
('Felipe Araújo', 'felipe.araujo@email.com', '123'),
('Larissa Almeida', 'larissa.almeida@email.com', '123'),
('Marcelo Ribeiro', 'marcelo.ribeiro@email.com', '123'),
('Bianca Fernandes', 'bianca.fernandes@email.com', '123');

INSERT INTO livro (titulo, autor, genero, ano_publicacao, disponivel, usuario_id) VALUES
('O Pequeno Príncipe', 'Dan Brown', 'Drama', 1912, TRUE, 1),
('Dom Casmurro', 'Isabel Allende', 'Infanto-Juvenil', 1919, TRUE, 2),
('1984', 'Jane Austen', 'Ficção Científica', 1951, FALSE, 3),
('Cem Anos de Solidão', 'Stephen King', 'Terror', 1956, TRUE, 4),
('O Alquimista', 'Franz Kafka', 'Realismo Mágico', 1969, FALSE, 5),
('A Culpa é das Estrelas', 'Ray Bradbury', 'Ficção', 1924, FALSE, 6),
('Cidade de Papel', 'Franz Kafka', 'Romance', 1906, TRUE, 7),
('Orgulho e Preconceito', 'J.R.R. Tolkien', 'Terror', 1978, TRUE, 8),
('Harry Potter e a Pedra Filosofal', 'Jane Austen', 'Drama', 1956, TRUE, 9),
('O Senhor dos Anéis', 'John Green', 'Aventura', 1961, FALSE, 10),
('As Crônicas de Nárnia', 'Franz Kafka', 'Terror', 1913, FALSE, 11),
('A Revolução dos Bichos', 'William Golding', 'Romance', 1988, TRUE, 12),
('O Lobo da Estepe', 'Carlos Ruiz Zafón', 'Realismo Mágico', 1947, TRUE, 13),
('O Apanhador no Campo de Centeio', 'Ray Bradbury', 'Ficção', 1943, TRUE, 14),
('A Metamorfose', 'Dan Brown', 'Realismo Mágico', 1991, FALSE, 15),
('O Processo', 'Ernest Hemingway', 'Biografia', 1913, FALSE, 1),
('O Código Da Vinci', 'Jane Austen', 'Biografia', 2003, TRUE, 2),
('Anjos e Demônios', 'J.R.R. Tolkien', 'Infanto-Juvenil', 1953, TRUE, 3),
('Inferno', 'Ernest Hemingway', 'Terror', 2016, TRUE, 4),
('O Símbolo Perdido', 'Ernest Hemingway', 'Drama', 2003, FALSE, 5),
-- (continua com os demais livros...)
('Querido John', 'John Green', 'Terror', 1977, TRUE, 5);