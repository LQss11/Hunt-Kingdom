<?php

namespace ForumBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Constraints\DateTime;


/**
 * publication
 *
 * @ORM\Table(name="publication")
 * @ORM\Entity(repositoryClass="ForumBundle\Repository\publicationRepository")
 */
class publication
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     *
     * @ORM\Column(name="type", type="string", length=255)
     * @Assert\Image()
     * @Assert\NotBlank(message="plz enter an image")
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=255)
     */
    private $contenu;




    /**
     * @ORM\ManyToOne(targetEntity="MainBundle\Entity\User")
     * @ORM\JoinColumn(name="id_User", referencedColumnName="id")
     */
    private $id_User;



    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datePublication", type="datetime")
     */
    private $datePublication;




    /**
     * @ORM\OneToMany(targetEntity="commentaire", mappedBy="publication")
     */
    private $commentaire;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set type
     *
     * @param string $type
     *
     * @return publication
     */
    public function setType($type)
   {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * Set contenu
     *
     * @param string $contenu
     *
     * @return publication
     */
    public function setContenu($contenu)
    {
        $this->contenu = $contenu;

        return $this;
    }

    /**
     * Get contenu
     *
     * @return string
     */
    public function getContenu()
    {
        return $this->contenu;
    }

    /**
     * @return mixed
     */
    public function getIdUser()
    {
        return $this->id_User;
    }



    /**
     * Set datePublication
     *
     * @param \DateTime $datePublication
     *
     * @return publication
     */
    public function setdatePublication($datePublication)
    {

            $this->datePublication = $datePublication;
        return $this;

    }

    /**
     * Get datePublication
     *
     * @return \DateTime
     */
    public function getdatePublication()
    {
        return $this->datePublication;
    }




    public function __toString() {
        return $this->getContenu();
    }




    /**
     * Set idUser
     *
     * @param \MainBundle\Entity\User $idUser
     *
     * @return publication
     */
    public function setIdUser(\MainBundle\Entity\User $idUser = null)
    {
        $this->id_User = $idUser;

        return $this;
    }

    /**
     * @return mixed
     */
    public function getCommentaire()
    {
        return $this->commentaire;
    }

    /**
     * @param mixed $commentaire
     */
    public function setCommentaire($commentaire)
    {
        $this->commentaire = $commentaire;
    }


    /**
     * Constructor
     */
    public function __construct()
    {
        $this->commentaire = new \Doctrine\Common\Collections\ArrayCollection();
    }

    /**
     * Add commentaire
     *
     * @param \ForumBundle\Entity\commentaire $commentaire
     *
     * @return publication
     */
    public function addCommentaire(\ForumBundle\Entity\commentaire $commentaire)
    {
        $this->commentaire[] = $commentaire;

        return $this;
    }

    /**
     * Remove commentaire
     *
     * @param \ForumBundle\Entity\commentaire $commentaire
     */
    public function removeCommentaire(\ForumBundle\Entity\commentaire $commentaire)
    {
        $this->commentaire->removeElement($commentaire);
    }
}
