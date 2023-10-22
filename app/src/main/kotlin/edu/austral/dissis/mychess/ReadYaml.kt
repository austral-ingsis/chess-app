package edu.austral.dissis.mychess
import org.yaml.snakeyaml.Yaml
import java.io.FileInputStream

class ReadYaml {
    companion object {
        fun readYml(fileName : String): Map<String, Any> {
            val yaml = Yaml()

            // Cargar el archivo YAML
            val inputStream = FileInputStream(fileName)

            // Analizar el archivo YAML
            return yaml.load(inputStream) as Map<String, Any>
        }
    }

}