package com.example.a22110180_ktra60_ltdd.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://app.iotstar.vn:8081/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val productApi: ProductApi = retrofit.create(ProductApi::class.java)

    // Mock products for testing if API is not available
    fun getMockProducts(): List<com.example.a22110180_ktra60_ltdd.data.Product> {
        return listOf(
            com.example.a22110180_ktra60_ltdd.data.Product(
                id = 1,
                name = "Beef and Mustard Pie",
                price = 8.25,
                image = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800&q=80",
                description = "Preheat the oven to 150C/300F/Gas 2. Heat the oil in a large flameproof casserole dish over a medium-high heat. Add the beef and fry for 5-8 minutes, or until browned all over. Remove the beef from the dish and set aside. Add the wine to the dish and bring to the boil, scraping up any browned bits from the bottom of the dish with a wooden spoon. Add the stock, onion, carrots, thyme and mustard, and bring to the boil. Return the beef to the dish, cover with a lid and transfer to the oven. Cook for 2 hours, or until the beef is tender."
            ),
            com.example.a22110180_ktra60_ltdd.data.Product(
                id = 2,
                name = "Chicken Pie",
                price = 7.50,
                image = "https://images.unsplash.com/photo-1603133872878-684f208fb84b?w=800&q=80",
                description = "A delicious chicken pie with creamy sauce and vegetables. Tender chicken pieces cooked in a rich, creamy sauce with fresh vegetables, all wrapped in flaky golden pastry."
            ),
            com.example.a22110180_ktra60_ltdd.data.Product(
                id = 3,
                name = "Shepherd's Pie",
                price = 9.00,
                image = "https://images.unsplash.com/photo-1606755962773-d324e0a13086?w=800&q=80",
                description = "Traditional shepherd's pie with minced lamb and mashed potatoes. A classic comfort food with tender minced lamb, vegetables, and creamy mashed potatoes on top, baked until golden brown."
            ),
            com.example.a22110180_ktra60_ltdd.data.Product(
                id = 4,
                name = "Fish and Chips",
                price = 10.50,
                image = "https://images.unsplash.com/photo-1621996346565-e3dbc646d9a9?w=800&q=80",
                description = "Crispy battered fish served with golden chips. Fresh cod fillet in a light, crispy batter, served with hand-cut chips and tartar sauce."
            ),
            com.example.a22110180_ktra60_ltdd.data.Product(
                id = 5,
                name = "Beef Steak",
                price = 15.00,
                image = "https://images.unsplash.com/photo-1546833999-877af2a3523c?w=800&q=80",
                description = "Premium beef steak grilled to perfection. Tender, juicy steak seasoned with herbs and spices, served with roasted vegetables and your choice of sauce."
            )
        )
    }
}

