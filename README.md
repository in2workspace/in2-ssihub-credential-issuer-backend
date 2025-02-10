# Credential Issuer Backend

<!-- TOC -->
* [Credential Issuer Backend](#credential-issuer-backend)
  * [Características incluidas en Credential Offer endpoint](#características-incluidas-en-credential-offer-endpoint)
  * [Características incluidas en Credential Issuer Metadata endpoint](#características-incluidas-en-credential-issuer-metadata-endpoint)
    * [Ejemplos de uso:](#ejemplos-de-uso)
    * [TODO](#todo)
  * [Características incluidas en OAuth 2.0 Authorization Server Metadata endpoint](#características-incluidas-en-oauth-20-authorization-server-metadata-endpoint)
    * [Ejemplos de uso:](#ejemplos-de-uso-1)
    * [TODO](#todo-1)
  * [Características incluidas en Token endpoint](#características-incluidas-en-token-endpoint)
    * [Ejemplos de uso:](#ejemplos-de-uso-2)
    * [TODO](#todo-2)
  * [Características incluidas en Nonce endpoint](#características-incluidas-en-nonce-endpoint)
    * [Ejemplos de uso:](#ejemplos-de-uso-3)
  * [Características incluidas en Credential endpoint](#características-incluidas-en-credential-endpoint)
    * [Ejemplos de uso:](#ejemplos-de-uso-4)
    * [TODO](#todo-3)
  * [Características incluidas en Deferred Credential endpoint](#características-incluidas-en-deferred-credential-endpoint)
    * [Ejemplos de uso:](#ejemplos-de-uso-5)
    * [TODO](#todo-4)
  * [Características incluidas en Notification endpoint](#características-incluidas-en-notification-endpoint)
    * [Ejemplos de uso:](#ejemplos-de-uso-6)
    * [TODO](#todo-5)
<!-- TOC -->

## Características incluidas en Credential Offer endpoint


## Características incluidas en Credential Issuer Metadata endpoint
- ✅ Exposición del endpoint en `/.well-known/openid-credential-issuer`.
- ✅ Cumplimiento de los parámetros de metadatos del Credential Issuer.
- ✅ Manejo de respuestas `application/json` con código `200 OK`.
- ✅ Soporte opcional para `authorization_servers`, `nonce_endpoint`, `deferred_credential_endpoint`, `notification_endpoint`.
- ✅ Configuración de credenciales soportadas con metadatos.

### Ejemplos de uso:

Solicitud válida:
```http request
GET /.well-known/openid-credential-issuer HTTP/1.1
Host: server.example.com
Accept-Language: en-US
```

Respuesta esperada:
```json
{
  "credential_issuer": "https://credential-issuer.example.com",
  "authorization_servers": ["https://auth.example.com"],
  "credential_endpoint": "https://credential-issuer.example.com/oid4vci/credential",
  "nonce_endpoint": "https://credential-issuer.example.com/oid4vci/nonce",
  "deferred_credential_endpoint": "https://credential-issuer.example.com/oid4vci/deferred_credential",
  "notification_endpoint": "https://credential-issuer.example.com/oid4vci/notification",
  "credential_response_encryption": {
    "alg_values_supported": ["ES256"],
    "enc_values_supported": ["A256GCM"],
    "encryption_required": true
  },
  "credential_configurations_supported": {
    "UniversityDegreeCredential": {
      "format": "jwt_vc_json",
      "cryptographic_binding_methods_supported": ["jwk"],
      "credential_signing_alg_values_supported": ["ES256"],
      "display": [
        {
          "name": "LEARCredentialEmployee",
          "locale": "en-US",
          "background_color": "#12107c",
          "text_color": "#FFFFFF"
        }
      ],
      "proof_types_supported": {
        "jwt": {
          "proof_signing_alg_values_supported": ["ES256"]
        }
      }
    }
  }
}
```

### TODO
- Incluir `signed_metadata` para firmar los metadatos del Credential Issuer.
- Soporte dinámico de múltiples credenciales en `credential_configurations_supported`.
- Personalización de `display` con más idiomas.

## Características incluidas en OAuth 2.0 Authorization Server Metadata endpoint
- ✅ Exposición del endpoint en `/.well-known/oauth-authorization-server`.
- ✅ Soporte para `pre-authorized_grant_anonymous_access_supported.
- ✅ Cumplimiento de los parámetros de metadatos del Authorization Server (`RFC8414`).
- ✅ Manejo de respuestas `application/json` con código `200 OK`.

### Ejemplos de uso:

Solicitud válida:
```http request
GET /.well-known/oauth-authorization-server HTTP/1.1
Host: auth.example.com
```

Respuesta esperada:
```json
{
  "issuer": "https://auth.example.com",
  "authorization_endpoint": "https://auth.example.com/oauth2/authorize",
  "token_endpoint": "https://auth.example.com/oauth2/token",
  "jwks_uri": "https://auth.example.com/.well-known/jwks.json",
  "grant_types_supported": [
    "authorization_code",
    "urn:ietf:params:oauth:grant-type:pre-authorized_code"
  ],
  "response_types_supported": ["code"],
  "pre-authorized_grant_anonymous_access_supported": true
}
```

### TODO
- Implementar JWKS (`/.well-known/jwks.json`) para firma y validación de tokens.
- Personalizar `grant_types_supported` según necesidades del Authorization Server.
- Soporte para OpenID Connect (`/.well-known/openid-configuration).

## Características incluidas en Token endpoint
- ✅ Manejo del Token Endpoint (/token).
- ✅ Procesamiento del pre-authorized_code y tx_code.
- ✅ Generación de un Access Token simulado (en una implementación real, usaríamos OAuth2 con JWT).
- ✅ Manejo de errores según la especificación (invalid_request, invalid_grant, invalid_client).
- ✅ Incluye el parámetro authorization_details en la respuesta.

### Ejemplos de uso:

Solicitud válida:
```http request
POST /oid4vci/token HTTP/1.1
Host: server.example.com
Content-Type: application/json

{
  "grant_type": "urn:ietf:params:oauth:grant-type:pre-authorized_code",
  "pre-authorized_code": "SplxlOBeZQQYbYS6WxSbIA",
  "tx_code": "493536"
}
```

Respuesta esperada:
```json
{
  "access_token": "3f5e1d08-0c2a-4a4d-93e5-91c1d4d8b0b5",
  "token_type": "Bearer",
  "expires_in": 86400,
  "authorization_details": [
    {
      "type": "openid_credential",
      "credential_configuration_id": "UniversityDegreeCredential",
      "credential_identifiers": ["CivilEngineeringDegree-2023", "ElectricalEngineeringDegree-2023"]
    }
  ]
}
```

Error: `pre-authorized_code` faltante:
```http request
POST /oid4vci/token HTTP/1.1
Host: server.example.com
Content-Type: application/json

{
  "grant_type": "urn:ietf:params:oauth:grant-type:pre-authorized_code"
}
```

Respuesta esperada:
```json
{
  "error": "invalid_request"
}
```

Error: `grant_type` incorrecto
```http request
POST /oid4vci/token HTTP/1.1
Host: server.example.com
Content-Type: application/json

{
  "grant_type": "authorization_code",
  "pre-authorized_code": "SplxlOBeZQQYbYS6WxSbIA"
}
```

Respuesta esperada:
```json
{
  "error": "invalid_grant"
}
```

### TODO
- Usar JWT en lugar de un UUID para el access_token.
- Almacenar los códigos pre-autorizados en una base de datos.
- Manejar autenticación y autorización con OAuth2 y Spring Security.

## Características incluidas en Nonce endpoint
- ✅ Manejo del Nonce Endpoint (/nonce).
- ✅ Generación de un c_nonce impredecible usando UUID (puedes cambiarlo por un mecanismo más seguro si lo deseas).
- ✅ Manejo de respuestas HTTP correctas (200 OK) con Cache-Control: no-store.
- ✅ Uso de @PostMapping para recibir peticiones HTTP POST.

### Ejemplos de uso:

Solicitud válida:
```http request
POST /oid4vci/nonce HTTP/1.1
Host: credential-issuer.example.com
```

Respuesta esperada:
```json
{
  "c_nonce": "a4d6f8b2c3e9487bb8f23a56"
}
```

## Características incluidas en Credential endpoint
- ✅ Validación de la estructura del request (credential_identifier o credential_configuration_id son excluyentes).
- ✅ Validación de la prueba de posesión (proof o proofs).
- ✅ Manejo de errores conforme a la especificación (invalid_credential_request, invalid_proof, invalid_nonce, etc.).
- ✅ Generación de credenciales simuladas en respuesta.
- ✅ Manejo de respuestas en flujo inmediato (200 OK) o diferido (202 Accepted).
- ✅ Soporte para credenciales cifradas con credential_response_encryption (por ahora simulado).

### Ejemplos de uso:

Solicitud válida:
```http request
POST /oid4vci/credential HTTP/1.1
Host: server.example.com
Authorization: Bearer czZCaGRSa3F0MzpnWDFmQmF0M2JW
Content-Type: application/json

{
  "credential_configuration_id": "org.iso.18013.5.1.mDL",
  "proof": {
    "proof_type": "jwt",
    "jwt": "eyJraWQiOiJkaWQ6ZXhhb..."
  }
}
```

Respuesta inmediata esperada:
```json
{
  "credentials": [
    {
      "credential": "b8a1f2c4-e789-4f23-b3a4-9281c672d729"
    }
  ]
}
```

Error: Falta Authorization:
```http request
POST /oid4vci/credential HTTP/1.1
Host: server.example.com
Content-Type: application/json
```

Respuesta esperada:
```json
{
  "error": "invalid_token",
  "error_description": "Missing or invalid access token."
}
```

Error: proof o proofs faltantes:
```json
{
  "error": "invalid_proof",
  "error_description": "Missing required proof parameter."
}
```

Error: nonce inválido:
```json
{
  "error": "invalid_nonce",
  "error_description": "Nonce value is invalid or expired."
}
```

### TODO
- Validar el JWT del `proof` antes de aceptar la solicitud.
- Soportar respuestas encriptadas con `credential_response_encryption`.
- Persistir `nonce` y tokens en BD para mayor seguridad.

## Características incluidas en Deferred Credential endpoint
- ✅ Validación de la transacción (`transaction_id`).
- ✅ Verificación del token de autorización.
- ✅ Emisión de credenciales diferidas si están listas.
- ✅ Manejo de errores conforme a la especificación (`issuance_pending`, `invalid_transaction_id`).

### Ejemplos de uso:

Solicitud válida:
```http request
POST /oid4vci/deferred_credential HTTP/1.1
Host: server.example.com
Authorization: Bearer czZCaGRSa3F0MzpnWDFmQmF0M2JW
Content-Type: application/json

{
  "transaction_id": "8xLOxBtZp8"
}
```

Respuesta esperada:
```json
{
  "credentials": [
    {
      "credential": "b8a1f2c4-e789-4f23-b3a4-9281c672d729"
    },
    {
      "credential": "e5f2a7b3-7c56-4e21-a31a-49d3f59a2f89"
    }
  ],
  "notification_id": "3fwe98js"
}
```

Error: transaction_id no válido:
```json
{
  "error": "invalid_transaction_id",
  "error_description": "Transaction ID not found or already used."
}
```

Error: Credencial aún no disponible:
```json
{
  "error": "issuance_pending",
  "error_description": "Credential issuance still pending.",
  "interval": 5
}
```

### TODO
- Persistir `transaction_id` y credenciales diferidas en BD para evitar pérdidas de información.
- Soportar respuestas encriptadas.
- Manejo de reintentos con `interval` dinámico en base a cargas del sistema.

## Características incluidas en Notification endpoint
- ✅ Validación de la estructura del request (`notification_id` y `event` son obligatorios).
- ✅ Validación del token de autorización (`Authorization: Bearer ...`).
- ✅ Manejo de idempotencia (si se recibe el mismo `notification_id` varias veces, responde con éxito).
- ✅ Manejo de errores conforme a la especificación (`invalid_notification_id`, `invalid_notification_request`).
- ✅ Respuesta con `204 No Content` en caso de éxito.

### Ejemplos de uso:

Solicitud válida:
```http request
POST /oid4vci/notification HTTP/1.1
Host: server.example.com
Authorization: Bearer czZCaGRSa3F0MzpnWDFmQmF0M2JW
Content-Type: application/json

{
  "notification_id": "3fwe98js",
  "event": "credential_accepted"
}
```

Respuesta esperada:
```text
HTTP/1.1 204 No Content
```

Error: notification_id no válido:
```json
{
  "error": "invalid_notification_id",
  "error_description": "The notification_id in the Notification Request was invalid."
}
```

Error: event no válido:
```json
{
  "error": "invalid_notification_request",
  "error_description": "Unsupported event type."
}
```

Error: Falta Authorization:
```json
{
  "error": "invalid_token",
  "error_description": "Missing or invalid access token."
}
```

### TODO
- Persistir notificaciones en BD para trazabilidad.
- Manejo de reintentos en caso de error temporal del emisor.


