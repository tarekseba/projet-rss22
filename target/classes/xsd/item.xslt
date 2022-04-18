<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:rss="http://univrouen.fr/rss22" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>RSS33</title>
                <link rel="stylesheet" href="./css/flux.css" type="text/css" />
            </head>
            <body>
                <h1>Affichage de la liste des articles stockés</h1>
                <table>
                    <thead>
                        <th>Guid</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Date</th>
                    </thead>
                    <tbody>
                        <xsl:for-each select="rss:feed/rss:item">
                            <tr>
                                <td><xsl:value-of select="rss:guid"/> </td>
                                <td><xsl:value-of select="rss:title"/> </td>
                                <td><xsl:value-of select="rss:category/@term"/></td>
                                <td>
                                    <xsl:value-of select="rss:published"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>


</xsl:stylesheet>