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
        $media1->setPath('https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.grandviewoutdoors.com%2Fpredator-hunting%2F4-top-predator-hunting-gear-products-for-2019&psig=AOvVaw3S5pfazn69lt30jHqZM7M2&ust=1582033883934000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNin6M7d2OcCFQAAAAAdAAAAABAD');
        $media1->setAlt('Bags');
        $manager->persist($media1);

        $media2 = new Media();
        $media2->setPath('https://www.google.com/url?sa=i&url=http%3A%2F%2Fwww.worldtradingpoint.com%2Fdetailp.php%3Fdetailp%3D81&psig=AOvVaw3S5pfazn69lt30jHqZM7M2&ust=1582033883934000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNin6M7d2OcCFQAAAAAdAAAAABAI');
        $media2->setAlt('Gloves');
        $manager->persist($media2);

        $media3 = new Media();
        $media3->setPath('https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.bushnell.com%2Fbuyers-guide%2Fdeer-hunting%2F&psig=AOvVaw3S5pfazn69lt30jHqZM7M2&ust=1582033883934000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNin6M7d2OcCFQAAAAAdAAAAABAO');
        $media3->setAlt('animals');
        $manager->persist($media3);

        $media4 = new Media();
        $media4->setPath('http://www.princedebretagne-pro.com/medias/5114fcd91ae7e.JPGg');
        $media4->setAlt('Piment');
        $manager->persist($media4);

        $media5 = new Media();
        $media5->setPath('https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.huntinggearguy.com%2Fequipment-reviews%2Fc-products-defense-50-beowulf-magazines%2F&psig=AOvVaw3S5pfazn69lt30jHqZM7M2&ust=1582033883934000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNin6M7d2OcCFQAAAAAdAAAAABAU');
        $media5->setAlt('Munition');
        $manager->persist($media5);

        $media6 = new Media();
        $media6->setPath('http://le-mag-de-lea.com/wp-content/uploads/Poivron-vert-11.jpg');
        $media6->setAlt('Poivron vert');
        $manager->persist($media6);

        $media7 = new Media();
        $media7->setPath('https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.recoilweb.com%2Fa-few-cool-hunting-products-from-shot-show-2020-157479.html&psig=AOvVaw3S5pfazn69lt30jHqZM7M2&ust=1582033883934000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNin6M7d2OcCFQAAAAAdAAAAABAa');
        $media7->setAlt('logo');
        $manager->persist($media7);

        $media8 = new Media();
        $media8->setPath('https://www.google.com/url?sa=i&url=https%3A%2F%2Fteespring.com%2Fdeer-hunting-and-ame-qp407e&psig=AOvVaw3S5pfazn69lt30jHqZM7M2&ust=1582033883934000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNin6M7d2OcCFQAAAAAdAAAAABAg');
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