package be.jidoka.clean.code.labs.gildedrose;

import java.util.Arrays;
import java.util.stream.IntStream;

class GildedRose {
    Item[] items; // TODO: Don't change because of goblin

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .map(NewItem::of)
                .forEach(NewItem::update);
    }

    private interface NewItem {

        static NewItem of(Item item) {
            switch (item.name) {
                case "Backstage passes to a TAFKAL80ETC concert":
                    return new ConcertItem(item);
                case "Aged Brie":
                    return new ImprovingItem(item);
                case "Sulfuras, Hand of Ragnaros":
                    return new LegendaryItem(item);
                case "Conjured Mana Cake":
                    return new ConjuredItem(item);
                default:
                    return new DegradingItem(item);
            }
        }

        void update();

        abstract class AbstractItem implements NewItem {

            protected final Item item;

            protected AbstractItem(Item item) {
                this.item = item;
            }

            @Override
            public final void update() {
                this.item.sellIn = this.item.sellIn + sellInDegradationRate();
                this.item.quality = Math.max(this.item.quality + qualityDegradationRate(), 0);
            }

            protected int sellInDegradationRate() {
                return -1;
            }

            protected abstract int qualityDegradationRate();

        }

        class DegradingItem extends AbstractItem implements NewItem {

            protected DegradingItem(Item item) {
                super(item);
            }

            @Override
            protected int qualityDegradationRate() {
                if (item.sellIn >= 0) {
                    return -1;
                } else {
                    return -2;
                }
            }

        }

        class ImprovingItem extends AbstractItem implements NewItem {

            protected ImprovingItem(Item item) {
                super(item);
            }

            @Override
            protected int qualityDegradationRate() {
                if (item.quality < 50) {
                    if (item.sellIn >= 0) {
                        return 1;
                    } else {
                        return 2;
                    }
                } else {
                    return 0;
                }
            }
        }

        class LegendaryItem extends AbstractItem implements NewItem {

            protected LegendaryItem(Item item) {
                super(item);
            }

            @Override
            protected int sellInDegradationRate() {
                // no-op: legendary items do not change!
                return 0;
            }

            @Override
            protected int qualityDegradationRate() {
                // no-op: legendary items do not change!
                return 0;
            }

        }

        class ConcertItem extends AbstractItem implements NewItem {

            protected ConcertItem(Item item) {
                super(item);
            }

            @Override
            protected int qualityDegradationRate() {
                if (IntStream.rangeClosed(6, 10).anyMatch(i -> i == item.sellIn)) {
                    return 2;
                } else if (IntStream.rangeClosed(0, 5).anyMatch(i -> i == item.sellIn)) {
                    return 3;
                } else if (item.sellIn < 0) {
                    return -item.quality;
                } else {
                    return 1;
                }
            }
        }

        class ConjuredItem extends AbstractItem implements NewItem {

            protected ConjuredItem(Item item) {
                super(item);
            }

            @Override
            protected int qualityDegradationRate() {
                if (item.sellIn >= 0) {
                    return -2;
                } else {
                    return -4;
                }
            }
        }
    }
}
