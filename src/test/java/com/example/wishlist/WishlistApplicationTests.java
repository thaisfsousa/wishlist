package com.example.wishlist;

import com.example.wishlist.bdd.WishlistStories;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WishlistApplicationTests {

	@Test
	void contextLoads() {
		new WishlistStories().run();
	}

}
