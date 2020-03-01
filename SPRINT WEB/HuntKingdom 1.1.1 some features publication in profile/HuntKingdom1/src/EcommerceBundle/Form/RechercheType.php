<?php
namespace EcommerceBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
class RechercheType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('recherche','text', array('label' => false,
                                                'attr' => array('class' => 'input-medium search-query')));
    }
    
    public function getName()
    {
        return 'ecommercebundle_recherche';
    }
}