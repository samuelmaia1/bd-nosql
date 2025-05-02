
# 💸 API de Transações - Java + Spring Boot

Esta é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar transações financeiras. Permite cadastrar, listar, atualizar e remover transações de forma simples e eficiente.

---

## 📌 Endpoints Disponíveis

| Método | Endpoint             | Descrição                        |
|--------|----------------------|----------------------------------|
| GET    | /transactions        | Lista todas as transações        |
| GET    | /transactions/{id}   | Busca uma transação por ID       |
| GET    | /transactions/orderByAmount | Lista todas as transações de forma ordenada |
| POST   | /transactions/add    | Cria uma nova transação          |
| PUT    | /transactions/{id}   | Atualiza uma transação existente |
| DELETE | /transactions/{id}   | Remove uma transação             |

---

## 🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Web
- MongoDB
- Lombok
- Maven

---

## 📑 Endpoints Detalhados

### ➕ Criar Transação

**POST /transactions/add**  

**Requisição:**

```json
{
  "title": "Compra no mercado",
  "amount": 1200.00,
  "description": "Compra de mantimentos no supermercado",
  "date": "25/05/2025",
  "category": "Alimentação",
  "type": "EXPENSE"
}
```

**Resposta:**

```json
{
  "id": "e7c23e7a-46b4-4dc2-8b6e-df6ec8b1f002",
  "title": "Compra no mercado",
  "amount": 1200.00,
  "description": "Compra de mantimentos no supermercado",
  "date": "25/05/2025",
  "category": "Alimentação",
  "type": "EXPENSE"
}
```

---

### 📃 Listar Todas as Transações

**GET /transactions**  

**Resposta:**

```json
[
  {
    "id": "c5638c41-3a2c-4e57-91dd-5ce5baa1dad1",
    "description": "Aluguel",
    "amount": 1200.00,
    "date": "25/05/2025",
    "type": "EXPENSE"
  },
  {
    "id": "c5638c41-3a2c-4e57-91dd-5ce5baa1dad2",
    "description": "Salário",
    "amount": 5000.00,
    "date": "23/02/2025",
    "type": "INCOME"
  }
]
```

---

### 🔍 Buscar Transação por ID

**GET /transactions/{id}**  

**Resposta:**

```json
{
  "id": "c5638c41-3a2c-4e57-91dd-5ce5baa1dad1",
  "title": "Compra no mercado",
  "amount": 1200.00,
  "description": "Compra de mantimentos no supermercado",
  "date": "25/05/2025",
  "category": "Alimentação",
  "type": "EXPENSE"
}
```

### 🔍 Buscar Transação Ordenada por Valor

**GET /transactions/orderByAmount/1 ou -1**  

**Resposta:**

**Resposta:**

```json
[
  {
    "id": "c5638c41-3a2c-4e57-91dd-5ce5baa1dad2",
    "description": "Salário",
    "amount": 5000.00,
    "date": "23/02/2025",
    "type": "INCOME"
  },
  {
    "id": "c5638c41-3a2c-4e57-91dd-5ce5baa1dad1",
    "description": "Aluguel",
    "amount": 1200.00,
    "date": "25/05/2025",
    "type": "EXPENSE"
  }
]
```

---

### ✏️ Atualizar Transação

**PUT /transactions/{id}**  

**Requisição:**

```json
{
  "title": "Compra no mercado",
  "description": "Compra de mantimentos no supermercado do Tio Sam",
  "amount": 300.75,
  "type": "EXPENSE",
  "category": "Alimentação"
}
```

**Resposta:**

```json
{
  "id": "c5638c41-3a2c-4e57-91dd-5ce5baa1dad1",
  "title": "Compra no mercado",
  "amount": 300.75,
  "description": "Compra de mantimentos no supermercado do Tio Sam",
  "date": "25/05/2025",
  "category": "Alimentação",
  "type": "EXPENSE"
}
```

---

### 🗑️ Remover Transação

**DELETE /transactions/{id}**  

**Resposta:**

```
204 No Content
```

---
