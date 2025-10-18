package com.alphazetakapp.stjosephappofficial.data.datasource.remote

import android.content.Context
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseStorageService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference
    
    // Directorio local para cache de audios
    private val cacheDir = File(context.cacheDir, "audio_cache")
    
    init {
        // Crear directorio de cache si no existe
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
    }
    
    /**
     * Obtiene la URL de descarga de un audio desde Firebase Storage
     * Si el archivo ya está en cache local, devuelve la ruta local
     */
    suspend fun getAudioUrl(audioFileName: String): String {
        val localFile = File(cacheDir, audioFileName)
        
        // Si el archivo ya existe en cache, devolver la ruta local
        if (localFile.exists()) {
            return localFile.absolutePath
        }
        
        // Si no existe, descargarlo desde Firebase Storage
        return downloadAudioFromFirebase(audioFileName, localFile)
    }
    
    /**
     * Descarga un audio desde Firebase Storage y lo guarda en cache local
     */
    private suspend fun downloadAudioFromFirebase(audioFileName: String, localFile: File): String {
        return try {
            val audioRef: StorageReference = storageRef.child(audioFileName)
            
            // Descargar el archivo
            audioRef.getFile(localFile).await()
            
            // Devolver la ruta del archivo local
            localFile.absolutePath
        } catch (e: Exception) {
            throw Exception("Error descargando audio $audioFileName: ${e.message}")
        }
    }
    
    /**
     * Obtiene la URL directa de Firebase Storage (sin descargar)
     * Útil para streaming directo
     */
    suspend fun getDirectDownloadUrl(audioFileName: String): String {
        return try {
            val audioRef: StorageReference = storageRef.child(audioFileName)
            audioRef.downloadUrl.await().toString()
        } catch (e: Exception) {
            throw Exception("Error obteniendo URL directa para $audioFileName: ${e.message}")
        }
    }
    
    /**
     * Limpia el cache de audios
     */
    fun clearAudioCache() {
        try {
            cacheDir.listFiles()?.forEach { file ->
                if (file.isFile) {
                    file.delete()
                }
            }
        } catch (e: Exception) {
            // Log error si es necesario
        }
    }
    
    /**
     * Obtiene el tamaño del cache de audios
     */
    fun getCacheSize(): Long {
        return try {
            cacheDir.listFiles()?.sumOf { it.length() } ?: 0L
        } catch (e: Exception) {
            0L
        }
    }
}
