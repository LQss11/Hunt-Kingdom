<?php
namespace EcommerceBundle\DataFixtures\ORM;

use Doctrine\Common\DataFixtures\AbstractFixture;
use Doctrine\Common\DataFixtures\OrderedFixtureInterface;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\Persistence\ObjectManager;
use EcommerceBundle\Entity\Media;

class MediaData extends AbstractFixture implements OrderedFixtureInterface
{
    public function load(ObjectManager $manager)
    {
        $media1 = new Media();
        $media1->setPath('https://s7d3.scene7.com/is/image/LaCrosse/danner_13_pdp/75002');
        $media1->setAlt('Bags');
        $manager->persist($media1);

        $media2 = new Media();
        $media2->setPath('https://s7d3.scene7.com/is/image/LaCrosse/danner_13_pdp/75002');
        $media2->setAlt('Gloves');
        $manager->persist($media2);

        $media3 = new Media();
        $media3->setPath('https://bobwards-hyperstatic.appspot.com/w=580-h=580/www.bobwards.com/prodimages/49613-DEFAULT-l.jpg');
        $media3->setAlt('animals');
        $manager->persist($media3);

        $media4 = new Media();
        $media4->setPath('https://s7d3.scene7.com/is/image/LaCrosse/danner_13_pdp/75002');
        $media4->setAlt('Piment');
        $manager->persist($media4);

        $media5 = new Media();
        $media5->setPath('https://bobwards-hyperstatic.appspot.com/w=580-h=580/www.bobwards.com/prodimages/81602-DEFAULT-l.jpg');
        $media5->setAlt('Munition');
        $manager->persist($media5);

        $media6 = new Media();
        $media6->setPath('https://bobwards-hyperstatic.appspot.com/w=580-h=580/www.bobwards.com/prodimages/54098-DEFAULT-l.jpg');
        $media6->setAlt('Poivron vert');
        $manager->persist($media6);

        $media7 = new Media();
        $media7->setPath('https://s7d3.scene7.com/is/image/LaCrosse/danner_13_pdp/40271');
        $media7->setAlt('logo');
        $manager->persist($media7);

        $media8 = new Media();
        $media8->setPath('https://s7d3.scene7.com/is/image/LaCrosse/danner_13_pdp/75002');
        $media8->setAlt('Clothes');
        $manager->persist($media8);

        $manager->flush();

        $this->addReference('media1', $media1);
        $this->addReference('media2', $media2);
        $this->addReference('media3', $media3);
        $this->addReference('media4', $media4);
        $this->addReference('media5', $media5);
        $this->addReference('media6', $media6);
        $this->addReference('media7', $media7);
        $this->addReference('media8', $media8);
    }

    public function getOrder()
    {
        return 1;
    }
}