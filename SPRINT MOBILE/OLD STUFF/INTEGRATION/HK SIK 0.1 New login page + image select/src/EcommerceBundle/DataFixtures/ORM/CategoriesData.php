<?php
namespace EcommerceBundle\DataFixtures\ORM;

use Doctrine\Common\DataFixtures\AbstractFixture;
use Doctrine\Common\DataFixtures\OrderedFixtureInterface;
use Doctrine\Common\Persistence\ObjectManager;
use Doctrine\Bundle\FixturesBundle\Fixture;
use EcommerceBundle\Entity\Categories;

class CategoriesData extends AbstractFixture implements OrderedFixtureInterface
{
    public function load(ObjectManager $manager)
    {
        $categorie1 = new Categories();
        $categorie1->setNom('Armors');
        $categorie1->setImage($this->getReference('media1'));
        $manager->persist($categorie1);
       
        $categorie2 = new Categories();
        $categorie2->setNom('Clothes');
        $categorie2->setImage($this->getReference('media2'));
        $manager->persist($categorie2);
       
        $manager->flush();
       
        $this->addReference('categorie1', $categorie1);
        $this->addReference('categorie2', $categorie2);
    }
    
    public function getOrder()
    {
        return 2;
    }
}