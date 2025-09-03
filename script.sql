DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'tipo_agendamento_enum') THEN
CREATE TYPE tipo_agendamento_enum AS ENUM ('MANUTENCAO', 'INSTALACAO', 'CONSULTORIA', 'EMERGENCIA');
END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'prioridade_enum') THEN
CREATE TYPE prioridade_enum AS ENUM ('BAIXA', 'MEDIA', 'ALTA', 'URGENTE');
END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'avaliacao_enum') THEN
CREATE TYPE avaliacao_enum AS ENUM ('MUITO_RUIM', 'RUIM', 'REGULAR', 'BOM', 'MUITO_BOM');
END IF;
END
$$;

CREATE TABLE agendamento (
                             id_agendamento SERIAL PRIMARY KEY,
                             data_agendamento TIMESTAMP NOT NULL,
                             tipo_agendamento tipo_agendamento_enum NOT NULL,
                             observacoes TEXT,
                             agendamento_finalizado BOOLEAN DEFAULT FALSE,
                             prioridade prioridade_enum,
                             id_usuario INT NOT NULL,
                             id_fazenda INT NOT NULL
);


CREATE TABLE avaliacao (
                           id_avaliacao SERIAL PRIMARY KEY,
                           id_agendamento INT NOT NULL,
                           nota_avaliacao avaliacao_enum,
                           comentario TEXT,
                           data_avaliacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT fk_agendamento
                               FOREIGN KEY (id_agendamento)
                                   REFERENCES agendamento (id_agendamento)
                                   ON DELETE CASCADE
);

CREATE TABLE equipamentos_usados (
                                     id SERIAL PRIMARY KEY,
                                     id_agendamento INT NOT NULL,
                                     id_equipamento INT NOT NULL,
                                     nome_equipamento varchar(50),
                                     quantidade INT NOT NULL,
                                     CONSTRAINT fk_agendamento_equipamentos FOREIGN KEY (id_agendamento) REFERENCES agendamento (id_agendamento)
);

CREATE TABLE pecas_usadas (
                              id SERIAL PRIMARY KEY,
                              id_agendamento INT NOT NULL,
                              id_peca INT NOT NULL,
                              nome_peca varchar(50),
                              quantidade INT NOT NULL,
                              CONSTRAINT fk_agendamento_pecas FOREIGN KEY (id_agendamento) REFERENCES agendamento (id_agendamento)
);