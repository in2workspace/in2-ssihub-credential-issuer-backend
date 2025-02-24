<div align="center">
  <h1>IN2 Verifier API</h1>
  <span>by </span><a href="https://in2.es">in2.es</a>
  <p><p>

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=in2workspace_in2-ssihub-issuer-backend)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=bugs)](https://sonarcloud.io/summary/new_code?id=in2workspace_in2-ssihub-issuer-backend)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=in2workspace_in2-ssihub-issuer-backend)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=security_rating)](https://sonarcloud.io/dashboard?id=in2workspace_in2-ssihub-issuer-backend)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=in2workspace_in2-ssihub-issuer-backend)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=ncloc)](https://sonarcloud.io/dashboard?id=in2workspace_in2-ssihub-issuer-backend)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=coverage)](https://sonarcloud.io/summary/new_code?id=in2workspace_in2-ssihub-issuer-backend)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=in2workspace_in2-ssihub-issuer-backend)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=in2workspace_in2-ssihub-issuer-backend)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=in2workspace_in2-ssihub-issuer-backend)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_in2-ssihub-issuer-backend&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=in2workspace_in2-ssihub-issuer-backend)
</div>

# Introduction

## Run the application

To run the application, execute:

```bash
docker-compose up --build -d
```


## Metrics

### Global Counters
The Application defines two counters: one for the total processed requests and another for the total errors. 
This allows you to monitor the traffic volume and the failure rate in a centralized way.

### Latency Timer
A Timer is used to measure the global latency of the requests. 
This helps to detect possible bottlenecks in the processing.

### Tags
A tag ("module", "global") is used to identify that these metrics correspond to the global view of the system. 
You can expand the use of tags if you need to further segment the information 
(for example, by environment, region, etc.).
