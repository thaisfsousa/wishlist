package com.example.wishlist.bdd;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Arrays;
import java.util.List;

public class WishlistStories extends JUnitStories {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withDefaultFormats()
                        .withFormats(StoryReporterBuilder.ProvidedFormat.CONSOLE, StoryReporterBuilder.ProvidedFormat.TXT));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new AddProductToWishlistSteps(),
                new FindProductInWishlistSteps(),
                new GetProductsInWishlistSteps(),
                new RemoveProductFromWishlistSteps());
    }

    public List<String> storyPaths() {
        return Arrays.asList("add_product_to_wishlist.story",
                "find_product_in_wishlist.story",
                "get_products_in_wishlist.story",
                "remove_product_from_wishlist.story");
    }
}
