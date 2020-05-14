<?php

namespace MainBundle\Entity;
use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


use Symfony\Component\HttpFoundation\File\File;

use Mgilet\NotificationBundle\Annotation\Notifiable;
use Mgilet\NotificationBundle\NotifiableInterface;



/**
 * User
 *@package MainBundle\Entity
 * @ORM\Table(name="user")
 * @ORM\Entity(repositoryClass="MainBundle\Repository\UserRepository")
 * @Notifiable(name="user")
 */
class User extends BaseUser implements NotifiableInterface
{


    const ROLE_ADMIN = 'ROLE_ADMIN';
    const ROLE_USER = 'ROLE_USER';
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;
    /**
     * @var
     *
     * @ORM\Column(name="firstname", type="string", length=255, nullable=true)
     */
    protected $firstname;

        /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="datetime")
     */
    private $date;

    /**
     * @var
     *
     * @ORM\Column(name="lastname", type="string", length=255, nullable=true)
     */


    protected $lastname;

    /**
     * @ORM\OneToMany(targetEntity="EcommerceBundle\Entity\Commandes", mappedBy="utilisateur", cascade={"remove"},cascade={"persist"})
     * @ORM\JoinColumn(nullable=true)
     */
    private $commandes;

    /**
     * @ORM\OneToMany(targetEntity="EcommerceBundle\Entity\UtilisateursAdresses", mappedBy="utilisateur", cascade={"remove"},cascade={"persist"})
     * @ORM\JoinColumn(nullable=true)
     */
    private $adresses;

    /**
     *
     * @ORM\Column(name="image", type="string", length=255)
     * @Assert\Image()
     * @Assert\NotBlank(message="plz enter an image")
     */
    private $image;


    /**
     * @var
     *
     * @ORM\Column(name="phone", type="string", length=255, nullable=true)
     */
    protected $phone;


    /**
     * Get id
     *
     * @return int
     */


    public function getId()
    {
        return $this->id;
    }


    public function __construct()
    {
        parent::__construct();
        // your own logic
    }


    /**
     * Set firstname
     *
     * @param string $firstname
     *
     * @return User
     */
    public function setFirstname($firstname)
    {
        $this->firstname = $firstname;

        return $this;
    }

    /**
     * Get firstname
     *
     * @return string
     */
    public function getFirstname()
    {
        return $this->firstname;
    }

    /**
     * Set lastname
     *
     * @param string $lastname
     *
     * @return User
     */
    public function setLastname($lastname)
    {
        $this->lastname = $lastname;

        return $this;
    }

    /**
     * Get lastname
     *
     * @return string
     */
    public function getLastname()
    {
        return $this->lastname;
    }

    /**
     * Set phone
     *
     * @param string $phone
     *
     * @return User
     */
    public function setPhone($phone)
    {
        $this->phone = $phone;

        return $this;
    }

    /**
     * Get phone
     *
     * @return string
     */
    public function getPhone()
    {
        return $this->phone;
    }



    /**
     * Add commande.
     *
     * @param \EcommerceBundle\Entity\Commandes $commande
     *
     * @return User
     */
    public function addCommande(\EcommerceBundle\Entity\Commandes $commande)
    {
        $this->commandes[] = $commande;

        return $this;
    }

    /**
     * Remove commande.
     *
     * @param \EcommerceBundle\Entity\Commandes $commande
     *
     * @return boolean TRUE if this collection contained the specified element, FALSE otherwise.
     */
    public function removeCommande(\EcommerceBundle\Entity\Commandes $commande)
    {
        return $this->commandes->removeElement($commande);
    }

    /**
     * Get commandes.
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getCommandes()
    {
        return $this->commandes;
    }

    /**
     * Add adress.
     *
     * @param \EcommerceBundle\Entity\UtilisateursAdresses $adress
     *
     * @return User
     */
    public function addAdress(\EcommerceBundle\Entity\UtilisateursAdresses $adress)
    {
        $this->adresses[] = $adress;

        return $this;
    }

    /**
     * Remove adress.
     *
     * @param \EcommerceBundle\Entity\UtilisateursAdresses $adress
     *
     * @return boolean TRUE if this collection contained the specified element, FALSE otherwise.
     */
    public function removeAdress(\EcommerceBundle\Entity\UtilisateursAdresses $adress)
    {
        return $this->adresses->removeElement($adress);
    }

    /**
     * Get adresses.
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getAdresses()
    {
        return $this->adresses;
    }

        /**
     * Set date
     *
     * @param \DateTime $date
     *
     * @return User
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }


    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return User
     */
    public function setImage($image)
   {
        $this->image = $image;

        return $image;
    }
}
