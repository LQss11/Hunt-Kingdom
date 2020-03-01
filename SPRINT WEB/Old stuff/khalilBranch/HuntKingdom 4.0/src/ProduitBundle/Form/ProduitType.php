<?php

namespace ProduitBundle\Form;

use ProduitBundle\Entity\Categorie;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;

class ProduitType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')
            ->add('quantite')
            ->add('description')
            ->add('prix')
            ->add('idFournisseur')
            ->add('etatPromo')
            ->add('garantie')

            ->add('categorie',EntityType::class,array('class'=>Categorie::class,'choice_label'=>'nom','multiple'=>false))
            ->add('image', FileType::class, array(
                'data_class' => null
            ));

    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ProduitBundle\Entity\Produit'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'produitbundle_produit';
    }


}
