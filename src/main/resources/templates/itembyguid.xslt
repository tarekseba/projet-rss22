<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

    <xsl:output method="html" omit-xml-declaration="yes" />


    <xsl:template match="/">
        <html>
            <head>
                <title>RSS22 Au format HTML</title>
                <style>
                    table {
                    font-family: arial, sans-serif;
                    border-collapse: collapse;
                    width: 100%;
                    }

                    td, th {
                    border: 1px solid #dddddd;
                    text-align: left;
                    padding: 8px;
                    }

                    tr:nth-child(even) {
                    background-color: #dddddd;
                    }
                </style>
            </head>
            <body>
                <h1>Affichage de la liste des articles stockés</h1>
                <div>
                    <table>
                        <thead>
                            <th>guid</th>
                            <th>title</th>
                            <th>category</th>
                            <th>date</th>
                        </thead>
                        <tbody>
                            <xsl:for-each select="item">
                                <tr>
                                    <td><xsl:value-of select="guid"/> </td>
                                    <td><xsl:value-of select="title"/> </td>
                                    <td><xsl:value-of select="category/@term"/></td>
                                    <td>
                                        <xsl:if test="published !=''">
                                            <xsl:value-of select="published"/>
                                        </xsl:if>
                                        <xsl:if test="updated !=''">
                                            <xsl:value-of select="updated"/>
                                        </xsl:if>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>


</xsl:stylesheet>