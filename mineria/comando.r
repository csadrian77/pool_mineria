setwd("C:/mineria/")
datos<-read.csv("datos.csv",sep=",",header=TRUE)
plot(
    datos$nonce_distancia, datos$tiempo,
    pch = 19,
    xlab = expression(x[2] ~ "(Nonce Distancia)"),
    ylab = expression(x[3] ~ "(Tiempo)"),
    main = "Relación entre Nonce Distancia y Tiempo"
)
# Excluir la columna no numérica
datos_numericos <- datos[, -1]  # Excluye la primera columna

grupos<-kmeans(datos_numericos,5,iter.max=100)
grupos

points(grupos$centers, pch= 19, col="blue", cex=2 )
points(datos_numericos, col= grupos$cluster+1, pch=19 )

barplot(grupos$centers[1,],col='blue')
barplot(grupos$centers[2,],col='red')

rownames(grupos$centers)<-c("cluster1","Cluster2","Cluster3","Cluster4","Cluster5")
barplot(t(grupos$centers),beside=TRUE, col=heat.colors(5) )

//barcplor de clueter
barplot(grupos$centers[3,],col=c(1,2,3,4,5),las=2 )

--grabo a una tabla con los cluster
NDatos<-cbind(datos_numericos,Grupo=grupos$cluster )
head(NDatos)
write.csv(NDatos, file = "datos_grupos.csv", row.names = TRUE)

InerciaIC<-rep(0,30)
for(k in 1:30){
    grupos<-kmeans(datos_numericos,k)
    InerciaIC[k]<-grupos$tot.withinss
}
plot(InerciaIC, col="blue", type="b" )

grupos1<-kmeans(datos_numericos,5,iter.max=100)
grupos1
------------- calibracion

InerciaIC.Hartigan = rep(0,30)
InerciaIC.Lloyd = rep(0,30)
InerciaIC.Forgy = rep(0,30)
InerciaIC.MacQueen = rep(0,30)
for(k in 1:30){
    gruposx=kmeans(datos_numericos,k,iter.max=100, algorithm = "Hartigan-Wong")
    InerciaIC.Hartigan[k]=gruposx$tot.withinss
    gruposx=kmeans(datos_numericos,k,iter.max=100, algorithm = "Lloyd")
    InerciaIC.Lloyd[k]=gruposx$tot.withinss
    gruposx=kmeans(datos_numericos,k,iter.max=100, algorithm = "Forgy")
    InerciaIC.Forgy[k]=gruposx$tot.withinss
    gruposx=kmeans(datos_numericos,k,iter.max=100, algorithm = "MacQueen")
    InerciaIC.MacQueen[k]=gruposx$tot.withinss
}
plot(InerciaIC.Hartigan,col="blue",type="b")
points(InerciaIC.Lloyd,col="red",type="b")
points(InerciaIC.Forgy,col="green",type="b")
points(InerciaIC.MacQueen,col="magenta",type="b")
legend("topright",legend = c("Hartigan","Lloyd","Forgy","MacQueen"),col=c("blue","red","green","magenta"),lty=1,lwd=1 )

Hartigan <- 0
Lloyd <- 0
Forgy <- 0
MacQueen <- 0
for(i in 1:50){
    gruposx=kmeans(datos_numericos,5,iter.max=100, algorithm = "Hartigan-Wong")
    Hartigan <- Hartigan + gruposx$betweenss
    gruposx=kmeans(datos_numericos,5,iter.max=100, algorithm = "Lloyd")
    Lloyd <- Lloyd + gruposx$betweenss
    gruposx=kmeans(datos_numericos,5,iter.max=100, algorithm = "Forgy")
    Forgy <- Forgy + gruposx$betweenss
    gruposx=kmeans(datos_numericos,5,iter.max=100, algorithm = "MacQueen")
    MacQueen <- MacQueen + gruposx$betweenss
}
Hartigan/50
Lloyd/50
Forgy/50
MacQueen/50
