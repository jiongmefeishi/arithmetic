package cn.zqtao.learn.day3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @auther: zqtao
 * @description: 猫狗队列
 * @version: 1.0
 */
public class Code_14_DagCatQueue {

    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    /**
     * 实际进入猫狗队列的实体
     */
    public static class PetEnter {
        private Pet pet;
        private long count;

        public PetEnter(Pet tytp, long count) {
            this.pet = tytp;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public long getCount() {
            return count;
        }

        public String getEnterPetType() {
            return this.pet.getPetType();
        }
    }

    // 猫狗队列
    public static class DogCatQueue {
        private Queue<PetEnter> dogQueue;
        private Queue<PetEnter> catQueue;
        private long count;

        public DogCatQueue() {
            this.dogQueue = new LinkedList<>();
            this.catQueue = new LinkedList<>();
            this.count = 0;
        }

        public void add(Pet pet) {
            if (pet.getPetType().equals("dog"))
                dogQueue.add(new PetEnter(pet, count++));
            else if (pet.getPetType().equals("cat"))
                catQueue.add(new PetEnter(pet, count++));
            else
                throw new RuntimeException("err, not dog or cat");
        }

        public Pet pollAll() {
            if (!dogQueue.isEmpty() && !catQueue.isEmpty()) {
                if (this.catQueue.peek().count > this.dogQueue.peek().count) {
                    return this.dogQueue.poll().getPet();
                } else {
                    return this.catQueue.poll().getPet();
                }
            } else if (!dogQueue.isEmpty()) {
                return this.dogQueue.poll().getPet();
            } else if (!catQueue.isEmpty()) {
                return this.catQueue.poll().getPet();
            } else {
                throw new RuntimeException("err, queue is empty!");
            }
        }

        public Dog pollDog() {
            if (!this.isDogQueueEmpty()) {
                return (Dog) this.dogQueue.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        public Cat pollCat() {
            if (!this.isCatQueueEmpty()) {
                return (Cat) this.catQueue.poll().getPet();
            } else
                throw new RuntimeException("Cat queue is empty!");
        }

        public boolean isEmpty() {
            return this.dogQueue.isEmpty() && this.catQueue.isEmpty();
        }

        public boolean isDogQueueEmpty() {
            return this.dogQueue.isEmpty();
        }

        public boolean isCatQueueEmpty() {
            return this.catQueue.isEmpty();
        }

    }

    public static void main(String[] args) {
        DogCatQueue test = new DogCatQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogQueueEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }
    }

}
