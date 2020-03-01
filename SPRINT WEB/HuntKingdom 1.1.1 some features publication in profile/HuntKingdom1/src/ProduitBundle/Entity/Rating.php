<?php

namespace ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Rating
 *
 * @ORM\Table(name="rating")
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\RatingRepository")
 */
class Rating
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
     * @var int
     *
     * @ORM\Column(name="note", type="integer")
     */
    private $note;

    /**
     * @ORM\ManyToOne(targetEntity="Produit")
     * @ORM\JoinColumn(name="idProduit", referencedColumnName="id")
     */
    private $idProduit;
    /**
     * @ORM\ManyToOne(targetEntity="MainBundle\Entity\User")
     * @ORM\JoinColumn(name="idClient", referencedColumnName="id")
     */
    private $idClient;


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
     * Set idProduit
     *
     * @param \ProduitBundle\Entity\Produit $idProduit
     *
     * @return Rating
     */
    public function setIdProduit(\ProduitBundle\Entity\Produit $idProduit = null)
    {
        $this->idProduit = $idProduit;

        return $this;
    }

    /**
     * Get idProduit
     *
     * @return \ProduitBundle\Entity\Produit
     */
    public function getIdProduit()
    {
        return $this->idProduit;
    }

    /**
     * Set idClient
     *
     * @param \MainBundle\Entity\User $idClient
     *
     * @return Rating
     */
    public function setIdClient(\MainBundle\Entity\User $idClient = null)
    {
        $this->idClient = $idClient;

        return $this;
    }

    /**
     * Get idClient
     *
     * @return \MainBundle\Entity\User
     */
    public function getIdClient()
    {
        return $this->idClient;
    }

    /**
     * Set note
     *
     * @param integer $note
     *
     * @return Rating
     */
    public function setNote($note)
    {
        $this->note = $note;

        return $this;
    }

    /**
     * Get note
     *
     * @return integer
     */
    public function getNote()
    {
        return $this->note;
    }
}
