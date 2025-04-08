package com.example.tecnologias
import android.os.Bundle
import android.util.Base64
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import android.graphics.BitmapFactory
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIPrincipal()
        }
    }
}

@Composable
fun UIPrincipal() {
    val context = LocalContext.current
    val dbHelper = DBHelper(context)

    val productos = remember {
        mutableStateOf(dbHelper.obtenerProductos())
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // 🌟 Espacio extra arriba del título
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = " PRODUCTOS TECNOLOGICOS",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Productos disponibles",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier
                        .border(2.dp, Color(0xFF9C27B0), RoundedCornerShape(8.dp))
                        .padding(4.dp)
                ) {
                    IconButton(onClick = {
                        // TODO: Acción para agregar producto
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar Producto")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        items(productos.value) { producto ->
            TarjetaProducto(producto)
        }
    }
}

@Composable
fun TarjetaProducto(producto: Tec) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            producto.imagenBase64?.let { base64String ->
                val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                bitmap?.let {

                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = producto.nombre,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(end = 8.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(text = producto.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = "$${producto.precio}", style = MaterialTheme.typography.bodyMedium)
                Text(text = producto.descripcion ?: "", style = MaterialTheme.typography.bodySmall)
            }

            Column {
                IconButton(onClick = {
                    // TODO: Editar
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = {
                    // TODO: Eliminar
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Previsualizacion() {
    UIPrincipal()
}
