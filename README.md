# RAG Agent Platform - MVP

RAG Agent Platform is a multi-tenant agent SaaS platform. Built on LLMs, RAG, and MCP, it lets you build knowledge bases and retrieval-augmented pipelines, quickly create and orchestrate agents/LLM bots, and plug in, switch, and configure model providers with one click.

面向多租户的智能体 SaaS平台。基于 LLM、RAG 与 MCP，您可搭建知识库与检索增强链路，快速创建和编排 Agent/LLM 机器人，并一键接入、切换与配置各类模型服务商。


## Requisites
- Java 17, Spring Boot 3
- PostgreSQL
- PGVector
- RabbitMQ
- **Object Storage Service**
  - Amazon S3
  - ✅ Qiniu Cloud Object Storage (KODO)
  - Tencent Cloud Object Storage (COS) 
- **Model Provider** 
  - ✅ Silicon Flow 硅基流动 (https://www.siliconflow.com/)
  - SiliconFlow is an AI platform that gives developers a single API to run many models for text, embeddings, reranking, and multimodal tasks.
  - 硅基流动是面向开发者的 AI 模型与算力平台，聚合开源/商用大模型并提供统一 API 的文本、向量、重排与多模态推理服务。
