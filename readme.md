# Supple Http Error --- Biblioteca de Padronização de Erros para APIs e SDKs

## Visão Geral

**Supple Http Error** é uma biblioteca Java projetada para **padronizar a
modelagem, propagação e serialização de erros** em aplicações backend,
APIs HTTP e SDKs, independentemente de framework.

A biblioteca fornece uma abstração consistente baseada nos princípios do
**RFC-7807 (Problem Details for HTTP APIs)**, permitindo que sistemas
diferentes compartilhem um **contrato de erro estável, tipado e
extensível**, sem acoplamento a tecnologias específicas como Spring
Boot.

------------------------------------------------------------------------

## Objetivo

Resolver problemas comuns no tratamento de erros:

- formatos de erro inconsistentes entre serviços
- dependência de mensagens textuais
- dificuldade de observabilidade e debugging
- ausência de contratos de erro tipados
- acoplamento entre domínio e framework HTTP
- reutilização difícil em SDKs e bibliotecas

A biblioteca define **um modelo universal de erro**, enquanto cada
aplicação mantém controle sobre o seu próprio catálogo de erros.

------------------------------------------------------------------------

## Princípios de Design

- Framework-agnostic (não depende de Spring)
- Compatível com RFC-7807
- Orientado a contratos, não mensagens
- Extensível para APIs e SDKs
- Separação entre infraestrutura e domínio
- Seguro para uso distribuído (microservices)
- Observability-friendly

------------------------------------------------------------------------

## Conceitos Fundamentais

### RFCError

Define o contrato base para qualquer erro exposto externamente.

``` java
public interface RFCError {
    String code();

    HttpStatusCode status();

    String title();

    String defaultMessage();
}
```

Representa **o tipo do erro**, não sua ocorrência específica.

------------------------------------------------------------------------

### RFCValidationError

Extensão do erro base para cenários de validação.

``` java
public interface RFCValidationError extends RFCError {
    List<FieldValidationError> errors();
}
```

------------------------------------------------------------------------

### FieldValidationError

Representa um erro de validação individual.

``` java
public record FieldValidationError(
        String field,
        String message,
        Object rejectedValue
) {}
```

------------------------------------------------------------------------

### HttpStatusCode

Enum independente contendo todos os códigos HTTP padronizados.

Características:

- independente de frameworks
- serializável
- lookup por código numérico
- helpers semânticos (`is4xxClientError`, etc.)

------------------------------------------------------------------------

### RFCException

Exceção base utilizada para propagar erros catalogados.

``` java
throw new RFCException(ErrorCatalog.USER_NOT_FOUND);
```

------------------------------------------------------------------------

### RFCValidationException

Especialização para erros de validação com múltiplos campos.

``` java
throw new RFCValidationException(error, validationErrors);
```

------------------------------------------------------------------------

## Aplicação consumidora

Cada sistema define seu próprio catálogo:

``` java
public enum ErrorCatalog implements RFCError {
    USER_NOT_FOUND(...),

    INVALID_REQUEST(...);
}
```

------------------------------------------------------------------------

## Separação de Responsabilidades

Responsabilidade Biblioteca Aplicação
  --------------------- ------------ -----------
Contrato de erro ✅           
Modelagem RFC ✅           
Exceptions base ✅           
Catálogo de domínio ✅
Serialização HTTP ✅
Framework adapters opcional ✅

------------------------------------------------------------------------

## Benefícios

### Para APIs

- contrato de erro consistente
- respostas previsíveis
- integração simples com tracing/logging
- suporte nativo a validação estruturada

### Para SDKs

- parsing tipado de erros
- independência de HTTP framework
- reutilização entre clientes

### Para arquitetura distribuída

- códigos estáveis entre serviços
- melhor observabilidade
- debugging simplificado via traceId

------------------------------------------------------------------------

## Exemplo de Fluxo

    Domain Service
         ↓
    throw RFCException(ErrorCatalog.USER_NOT_FOUND)
         ↓
    Framework Adapter
         ↓
    RFC Problem Response
         ↓
    Client / SDK

------------------------------------------------------------------------

## Exemplo de Resposta (RFC-7807)

``` json
{
  "title": "Resource not found",
  "status": 404,
  "detail": "User not found",
  "code": "USER_NOT_FOUND",
  "traceId": "4f3a2c1e9a0b7d12"
}
```

------------------------------------------------------------------------

## Quando Usar

Use esta biblioteca quando:

- múltiplas APIs precisam de consistência
- SDKs consomem suas APIs
- você deseja contratos de erro estáveis
- há necessidade de observabilidade padronizada
