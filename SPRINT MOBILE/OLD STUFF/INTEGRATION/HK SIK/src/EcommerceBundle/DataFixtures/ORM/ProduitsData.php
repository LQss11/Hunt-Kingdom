<?php
namespace EcommerceBundle\DataFixtures\ORM;

use Doctrine\Common\DataFixtures\AbstractFixture;
use Doctrine\Common\DataFixtures\OrderedFixtureInterface;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\Persistence\ObjectManager;
use EcommerceBundle\Entity\Produits;

class ProduitsData extends AbstractFixture implements OrderedFixtureInterface
{
    public function load(ObjectManager $manager)
    {
        $produit1 = new Produits();
        $produit1->setCategorie($this->getReference('categorie1'));
        $produit1->setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facilis voluptate placeat, architecto dicta tenetur labore magnam fugit. Om.");
        $produit1->setDisponible('1');
        $produit1->setImage($this->getReference('media3'));
        $produit1->setNom('Gloves');
        $produit1->setPrix('1.99');
        $produit1->setTva($this->getReference('tva2'));
        $manager->persist($produit1);

        $produit2 = new Produits();
        $produit2->setCategorie($this->getReference('categorie1'));
        $produit2->setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Non aut minima nisi ut magnam labore, tenetur similique amet aspernatur suscipit. Incidunt ducimus accusamus, magni illo velit voluptate nesciunt quasi aperiam");
        $produit2->setDisponible('1');
        $produit2->setImage($this->getReference('media4'));
        $produit2->setNom('Cars');
        $produit2->setPrix('3.99');
        $produit2->setTva($this->getReference('tva2'));
        $manager->persist($produit2);

        $produit3 = new Produits();
        $produit3->setCategorie($this->getReference('categorie1'));
        $produit3->setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo beatae architecto impedit nesciunt deleniti dolore hic possimus labore ducimus? Soluta esse voluptatem reprehenderit a ea quo consequuntur temporibus amet, similique?");
        $produit3->setDisponible('1');
        $produit3->setImage($this->getReference('media5'));
        $produit3->setNom('ARMES');
        $produit3->setPrix('0.99');
        $produit3->setTva($this->getReference('tva2'));
        $manager->persist($produit3);
        
        $produit4 = new Produits();
        $produit4->setCategorie($this->getReference('categorie1'));
        $produit4->setDescription("Le poivron vert est un groupe de cultivars de l'espèce Capsicum annuum.");
        $produit4->setDisponible('1');
        $produit4->setImage($this->getReference('media6'));
        $produit4->setNom('lunettes');
        $produit4->setPrix('2.99');
        $produit4->setTva($this->getReference('tva2'));
        $manager->persist($produit4);
        
        $produit5 = new Produits();
        $produit5->setCategorie($this->getReference('categorie2'));
        $produit5->setDescription(" Lorem ipsum dolor sit amet, consectetur adipisicing elit. Porro eos eum, similique illum mollitia est obcaecati, vel accusamus molestias dicta nesciunt a ea quo consequuntur id eaque iusto in! Libero!");
        $produit5->setDisponible('1');
        $produit5->setImage($this->getReference('media7'));
        $produit5->setNom('bottes');
        $produit5->setPrix('0.97');
        $produit5->setTva($this->getReference('tva2'));
        $manager->persist($produit5);
        
        $produit6 = new Produits();
        $produit6->setCategorie($this->getReference('categorie2'));
        $produit6->setDescription(" Lorem ipsum dolor sit amet, consectetur adipisicing elit. Porro eos eum, similique illum mollitia est obcaecati, vel accusamus molestias dicta nesciunt a ea quo consequuntur id eaque iusto in! Libero!");
        $produit6->setDisponible('1');
        $produit6->setImage($this->getReference('media8'));
        $produit6->setNom('socks');
        $produit6->setPrix('1.20');
        $produit6->setTva($this->getReference('tva2'));
        $manager->persist($produit6);
        
        $manager->flush();
    }
    
    public function getOrder()
    {
        return 4;
    }
}