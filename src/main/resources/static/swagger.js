var spec = {
    "openapi": "3.0.1",
    "info": {
        "version": "1.0.0",
        "title": "RSS-22 API"
    },
    "paths": {
        "/rss22/insert": {
            "post": {
                "summary": "Le flux reçu est validé par le schéma XSD de définition rss22.",
                "operationId": "addFlux",
                "tags": [
                    "Article API"
                ],
                "requestBody": {
                    "content": {
                        "application/xml": {
                            "schema": {
                                "$ref": "#/components/schemas/Article"
                            }
                        }
                    }
                },
                "responses": {
                    "201": {
                        "description": "Success",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/Article"
                                }
                            }
                        }
                    },
                    "400": {
                        "$ref": "#/components/responses/IllegalInput"
                    }
                }
            },
            "get": {
                "summary": "Get a list of articles",
                "operationId": "listArticles",
                "tags": [
                    "Article API"
                ],
                "parameters": [
                    {
                        "$ref": "#/components/parameters/Limit"
                    },
                    {
                        "$ref": "#/components/parameters/Offset"
                    }
                ],
                "responses": {
                    "200": {
                        "description": "Success",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ArticleList"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/articles/{id}": {
            "get": {
                "summary": "Get an article.",
                "operationId": "getArticle",
                "tags": [
                    "Article API"
                ],
                "parameters": [
                    {
                        "$ref": "#/components/parameters/Id"
                    }
                ],
                "responses": {
                    "200": {
                        "description": "Success",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/Article"
                                }
                            }
                        }
                    },
                    "404": {
                        "$ref": "#/components/responses/NotFound"
                    }
                }
            },
            "put": {
                "summary": "Update",
                "operationId": "updateArticle",
                "tags": [
                    "Article API"
                ],
                "parameters": [
                    {
                        "$ref": "#/components/parameters/Id"
                    }
                ],
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/Article"
                            }
                        }
                    }
                },
                "responses": {
                    "200": {
                        "description": "Success",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/Article"
                                }
                            }
                        }
                    },
                    "404": {
                        "$ref": "#/components/responses/NotFound"
                    }
                }
            },
            "delete": {
                "summary": "Delete an article.",
                "operationId": "deleteArticle",
                "tags": [
                    "Article API"
                ],
                "parameters": [
                    {
                        "$ref": "#/components/parameters/Id"
                    }
                ],
                "responses": {
                    "204": {
                        "description": "Success"
                    },
                    "404": {
                        "$ref": "#/components/responses/NotFound"
                    }
                }
            }
        }
    },
    "components": {
        "schemas": {
            "Id": {
                "description": "Resource ID",
                "type": "integer",
                "format": "int64",
                "readOnly": true,
                "example": 1
            },
            "ArticleForList": {
                "properties": {
                    "id": {
                        "$ref": "#/components/schemas/Id"
                    },
                    "category": {
                        "description": "Category of an article",
                        "type": "string",
                        "example": "sports"
                    }
                }
            },
            "Article": {
                "allOf": [
                    {
                        "$ref": "#/components/schemas/ArticleForList"
                    }
                ],
                "required": [
                    "xml"
                ],
                "properties": {
                    "item": {
                        "description": "Content of an article",
                        "type": "xml",
                        "maxLength": 1024,
                        "example": {
                            'guid':'388206d7-b404-433c-b4df-62c9687a59c8',
                            'title':'A title',
                            'category':{}
                        }
                    }
                }
            },
            "ArticleList": {
                "type": "array",
                "items": {
                    "$ref": "#/components/schemas/ArticleForList"
                }
            },
            "Error": {
                "description": "<table>\n  <tr>\n    <th>Code</th>\n    <th>Description</th>\n  </tr>\n  <tr>\n    <td>illegal_input</td>\n    <td>The input is invalid.</td>\n  </tr>\n  <tr>\n    <td>not_found</td>\n    <td>The resource is not found.</td>\n  </tr>\n</table>\n",
                "required": [
                    "code",
                    "message"
                ],
                "properties": {
                    "code": {
                        "type": "string",
                        "example": "illegal_input"
                    }
                }
            }
        },
        "parameters": {
            "Id": {
                "name": "id",
                "in": "path",
                "description": "Resource ID",
                "required": true,
                "schema": {
                    "$ref": "#/components/schemas/Id"
                }
            },
            "Limit": {
                "name": "limit",
                "in": "query",
                "description": "limit",
                "required": false,
                "schema": {
                    "type": "integer",
                    "minimum": 1,
                    "maximum": 100,
                    "default": 10,
                    "example": 10
                }
            },
            "Offset": {
                "name": "offset",
                "in": "query",
                "description": "offset",
                "required": false,
                "schema": {
                    "type": "integer",
                    "minimum": 0,
                    "default": 0,
                    "example": 10
                }
            }
        },
        "responses": {
            "NotFound": {
                "description": "The resource is not found.",
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/Error"
                        }
                    }
                }
            },
            "IllegalInput": {
                "description": "The input is invalid.",
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/Error"
                        }
                    }
                }
            }
        }
    }
}