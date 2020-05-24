<?php

namespace ForumBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use MainBundle\Entity\User;

/**
 * commentaire
 *
 * @ORM\Table(name="commentaire")
 * @ORM\Entity(repositoryClass="ForumBundle\Repository\commentaireRepository")
 */
class commentaire
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
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=255)
     */
    private $contenu;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateComnt", type="datetime")
     */
    private $dateComnt;


    /**
     * @ORM\ManyToOne(targetEntity="MainBundle\Entity\User")
     * @ORM\JoinColumn(name="id_User", referencedColumnName="id")
     */
    private $id_User;




    /**
     * @ORM\ManyToOne(targetEntity="publication",inversedBy="commentaire")
     * @ORM\JoinColumn(name="id_publication", referencedColumnName="id")
     */
    private $id_publication;


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
     * Set contenu
     *
     * @param string $contenu
     *
     * @return commentaire
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
     * Set dateComnt
     *
     * @param \DateTime $dateComnt
     *
     * @return commentaire
     * @throws \Exception
     */
    public function setDateComnt()
    {
          $this->dateComnt = new \dateTime('now');

        return $this;
    }

    /**
     * Get dateComnt
     *
     * @return \DateTime
     */
    public function getDateComnt()
    {
        return $this->dateComnt;
    }


    /**
     * Set idUser
     *
     * @param User $idUser
     *
     * @return commentaire
     */
    public function setIdUser(User $idUser = null)
    {
        $this->id_User = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return User
     */
    public function getIdUser()
    {
        return $this->id_User;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }




    /**
     * Set idPublication
     *
     * @param \ForumBundle\Entity\publication $idPublication
     *
     * @return commentaire
     */
    public function setIdPublication(\ForumBundle\Entity\publication $idPublication )
    {
        $this->id_publication = $idPublication;

        return $this;
    }

    /**
     * Get idPublication
     *
     * @return \ForumBundle\Entity\publication
     */
    public function getIdPublication()
    {
        return $this->id_publication;
    }

    public function __toString()
    {
       return $this->getContenu();
    }


}
