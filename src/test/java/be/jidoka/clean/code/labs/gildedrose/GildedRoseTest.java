package be.jidoka.clean.code.labs.gildedrose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    @Test
    void updateQualityOfDegradingItem() {
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("+5 Dexterity Vest");
        assertThat(items[0].sellIn).isEqualTo(9);
        assertThat(items[0].quality).isEqualTo(19);
    }

    @Test
    void updateQualityOfDegradingItemAfterSellDate() {
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", -1, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("+5 Dexterity Vest");
        assertThat(items[0].sellIn).isEqualTo(-2);
        assertThat(items[0].quality).isEqualTo(18);
    }

    @Test
    void updateQualityNeverNegative() {
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 5, 0)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("+5 Dexterity Vest");
        assertThat(items[0].sellIn).isEqualTo(4);
        assertThat(items[0].quality).isEqualTo(0);
    }

    @Test
    void updateQualityOfImprovingItem() {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("Aged Brie");
        assertThat(items[0].sellIn).isEqualTo(1);
        assertThat(items[0].quality).isEqualTo(1);
    }

    @Test
    void updateQualityOfImprovingItemAfterSellDate() {
        Item[] items = new Item[]{new Item("Aged Brie", -1, 5)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("Aged Brie");
        assertThat(items[0].sellIn).isEqualTo(-2);
        assertThat(items[0].quality).isEqualTo(7);
    }

    @Test
    void updateQualityNeverHigherThan50() {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 50)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("Aged Brie");
        assertThat(items[0].sellIn).isEqualTo(1);
        assertThat(items[0].quality).isEqualTo(50);
    }

    @Test
    void updateQualityOfLegendaryItem() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 0, 80)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("Sulfuras, Hand of Ragnaros");
        assertThat(items[0].sellIn).isEqualTo(0);
        assertThat(items[0].quality).isEqualTo(80);
    }

    @Test
    void updateQualityOfLegendaryItemAfterSellDate() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 80)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("Sulfuras, Hand of Ragnaros");
        assertThat(items[0].sellIn).isEqualTo(-1);
        assertThat(items[0].quality).isEqualTo(80);
    }

    @Nested
    class Concert {

        public static final String NAME = "Backstage passes to a TAFKAL80ETC concert";

        @Test
        void updateQualityOfConcertSellInAbove10() {
            Item[] items = new Item[]{new Item(NAME, 15, 20)};

            GildedRose app = new GildedRose(items);
            app.updateQuality();

            assertThat(items).hasSize(1);
            assertThat(items[0].name).isEqualTo(NAME);
            assertThat(items[0].sellIn).isEqualTo(14);
            assertThat(items[0].quality).isEqualTo(21);
        }

        @Test
        void updateQualityOfConcertSellInBetween5And10Old() {
            Item[] items = new Item[]{new Item(NAME, 10, 49)};

            GildedRose app = new GildedRose(items);
            app.updateQuality();

            assertThat(items).hasSize(1);
            assertThat(items[0].name).isEqualTo(NAME);
            assertThat(items[0].sellIn).isEqualTo(9);
            assertThat(items[0].quality).isEqualTo(51); // bug fix if you read the requirements!
        }

        @Test
        void updateQualityOfConcertSellInBelow5Old() {
            Item[] items = new Item[]{new Item(NAME, 5, 49)};

            GildedRose app = new GildedRose(items);
            app.updateQuality();

            assertThat(items).hasSize(1);
            assertThat(items[0].name).isEqualTo(NAME);
            assertThat(items[0].sellIn).isEqualTo(4);
            assertThat(items[0].quality).isEqualTo(52); // bug fix if you read the requirements!
        }

        @Test
        void updateQualityOfConcertOnConcertDate() {
            Item[] items = new Item[]{new Item(NAME, 1, 49)};

            GildedRose app = new GildedRose(items);
            app.updateQuality();

            assertThat(items).hasSize(1);
            assertThat(items[0].name).isEqualTo(NAME);
            assertThat(items[0].sellIn).isEqualTo(0);
            assertThat(items[0].quality).isEqualTo(52); // bug fix if you read the requirements!
        }

        @Test
        void updateQualityOfConcertSellInZero() {
            Item[] items = new Item[]{new Item(NAME, 0, 49)};

            GildedRose app = new GildedRose(items);
            app.updateQuality();

            assertThat(items).hasSize(1);
            assertThat(items[0].name).isEqualTo(NAME);
            assertThat(items[0].sellIn).isEqualTo(-1);
            assertThat(items[0].quality).isEqualTo(0);
        }

    }

    @Test // TODO: New requirement, make this test pass!
    void updateQualityOfConjuredItem() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("Conjured Mana Cake");
        assertThat(items[0].sellIn).isEqualTo(2);
        assertThat(items[0].quality).isEqualTo(4);
    }

    @Test // TODO: New requirement, make this test pass!
    void updateQualityOfConjuredItemAfterSellDate() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", -1, 6)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items).hasSize(1);
        assertThat(items[0].name).isEqualTo("Conjured Mana Cake");
        assertThat(items[0].sellIn).isEqualTo(-2);
        assertThat(items[0].quality).isEqualTo(2);
    }

}