package io.github.haappi.battleGame;

public class BaseEntity {
    private final String name;
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private int speed;
    private int mana;
    private int maxMana;

    public BaseEntity(EntityBuilder builder) {
        this.name = builder.name;
        this.health = builder.health;
        this.maxHealth = builder.maxHealth;
        this.attack = builder.attack;
        this.defense = builder.defense;
        this.speed = builder.speed;
        this.mana = builder.mana;
        this.maxMana = builder.maxMana;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int health) {
        this.health += health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void changeMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public void changeAttack(int attack) {
        this.attack += attack;
    }

    public int getDefense() {
        return defense;
    }

    public void changeDefense(int defense) {
        this.defense += defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void changeSpeed(int speed) {
        this.speed += speed;
    }

    public int getMana() {
        return mana;
    }

    public void changeMana(int mana) {
        this.mana += mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void changeMaxMana(int maxMana) {
        this.maxMana += maxMana;
    }

    public static class EntityBuilder {
        private final String name;
        private int health;
        private int maxHealth;
        private int attack;
        private int defense;
        private int speed;
        private int mana;
        private int maxMana;

        public EntityBuilder(String name) {
            this.name = name;
        }

        public EntityBuilder health(int health) {
            this.health = health;
            return this;
        }

        public EntityBuilder maxHealth(int maxHealth) {
            this.maxHealth = maxHealth;
            return this;
        }

        public EntityBuilder attack(int attack) {
            this.attack = attack;
            return this;
        }

        public EntityBuilder defense(int defense) {
            this.defense = defense;
            return this;
        }

        public EntityBuilder speed(int speed) {
            this.speed = speed;
            return this;
        }

        public EntityBuilder mana(int mana) {
            this.mana = mana;
            return this;
        }

        public EntityBuilder maxMana(int maxMana) {
            this.maxMana = maxMana;
            return this;
        }

        public BaseEntity build() {
            return new BaseEntity(this);
        }
    }
}
