USE bdFarmacia;

CREATE TABLE tbCliente(
	idCliente INT PRIMARY KEY AUTO_INCREMENT
    , logCliente VARCHAR (60) NOT NULL
    , numLog VARCHAR(5) NOT NULL
    , cepCliente VARCHAR(9) NOT NULL
    , bairroCliente VARCHAR(30) NOT NULL
    , ufCliente VARCHAR(2) NOT NULL
    , complementoLog VARCHAR(30) 
    , nomeCliente VARCHAR(60) NOT NULL
    , dataNasc VARCHAR(10) NOT NULL
    , cpfCliente VARCHAR(14) NOT NULL
    , generoCliente VARCHAR(1) NOT NULL
    , municipioCliente VARCHAR(60) NOT NULL
); 

CREATE TABLE tbFoneCliente(
	idFoneCliente INT PRIMARY KEY AUTO_INCREMENT
    , numFoneCliente INT NOT NULL
    , idCliente INT
    , FOREIGN KEY (idCliente) REFERENCES tbCliente(idCliente)
);    
    
CREATE TABLE tbFabricante(
	idFabricante INT PRIMARY KEY AUTO_INCREMENT
    , nomeFabricante VARCHAR(40) NOT NULL
);    

CREATE TABLE tbFornecedor(
	idFornecedor INT PRIMARY KEY AUTO_INCREMENT
    , nomeFornecedor VARCHAR(40) NOT NULL
);   


select * from tbFornecedor;
select * from tbCliente;
        

CREATE TABLE tbFuncionario(
	idFuncionario INT PRIMARY KEY AUTO_INCREMENT
    , nomeFuncionario VARCHAR(60) NOT NULL
    , rgFuncionario VARCHAR(14) NOT NULL
    , cpfFuncionario VARCHAR(14) NOT NULL
    , generoFuncionario VARCHAR(2) NOT NULL
);

select * from tbFuncionario;

CREATE TABLE tbFoneFuncionario(
	idFoneFuncionario INT PRIMARY KEY AUTO_INCREMENT
    , numFoneFuncionario INT NOT NULL
    , idFuncionario INT
    , FOREIGN KEY (idFuncionario) REFERENCES tbFuncionario(idFuncionario)
);    

CREATE TABLE tbProduto(
	idProduto INT PRIMARY KEY AUTO_INCREMENT
    , nomeProduto VARCHAR(40) NOT NULL
    , precoProduto DOUBLE NOT NULL
    , idFornecedor INT NOT NULL
    , idFabricante INT NOT NULL
    , tipoProduto VARCHAR(30) NOT NULL
    , FOREIGN KEY (idFornecedor) REFERENCES tbFornecedor(idFornecedor)
    , FOREIGN KEY (idFabricante) REFERENCES tbFabricante(idFabricante)
 );   
 
        
 CREATE TABLE tbVenda(
	idVenda INT PRIMARY KEY AUTO_INCREMENT
    , dataVenda DATE NOT NULL
    , valorTotalVenda DOUBLE NOT NULL
    , idCliente INT NOT NULL
    , FOREIGN KEY (idCliente) REFERENCES tbCliente(idCliente)
);   

CREATE TABLE tbItensVenda(
	idItensVenda INT PRIMARY KEY AUTO_INCREMENT
    , idVenda INT NOT NULL
    , idProduto INT NOT NULL
    , qtdItensVenda INT NOT NULL
    , subTotalVenda DOUBLE 
    , FOREIGN KEY (idVenda) REFERENCES tbVenda(idVenda)
    , FOREIGN KEY (idProduto) REFERENCES tbProduto(idProduto)
);

select * from tbCliente
select * from tbFuncionario

